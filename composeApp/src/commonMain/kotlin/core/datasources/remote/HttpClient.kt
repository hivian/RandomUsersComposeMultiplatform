package core.datasources.remote

import datasources.remote.ErrorType
import datasources.remote.HttpResult
import io.ktor.client.HttpClient
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

class HttpClient(private val baseUrl: String): IHttpClient {

    companion object {
        private const val TIMEOUT: Long = 30_000
    }

    private val client: HttpClient = HttpClient() {
        install(HttpTimeout) {
            socketTimeoutMillis = TIMEOUT
            requestTimeoutMillis = TIMEOUT
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        defaultRequest {
            header("Content-Type", "application/json")
            url(baseUrl)
        }
    }

    private val jsonConfig = Json { prettyPrint = true; ignoreUnknownKeys = true }

    @OptIn(InternalSerializationApi::class)
    override suspend fun <T : Any> get(urlPath: String, ofClass: KClass<T>, parameters: Map<String, String>?): HttpResult<T> {
        val response = client.get("$baseUrl/$urlPath") {
            url {
                parameters?.forEach { (key, value) ->
                    this.parameters.append(key, value)
                }
            }
        }.bodyAsText()

        return safeApiCall {
            jsonConfig.decodeFromString(ofClass.serializer(), response)
        }
    }

    private suspend fun <T : Any> safeApiCall(call: suspend () -> T) : HttpResult<T> {
        return try {
            val response = call.invoke()

            HttpResult.Success(response)
        } catch (exception: Exception) {
            when (exception) {
                is RedirectResponseException -> HttpResult.Error(ErrorType.REDIRECT)
                is ClientRequestException -> {
                    HttpResult.Error(
                        when (exception.response.status) {
                            HttpStatusCode.Unauthorized, HttpStatusCode.Forbidden -> ErrorType.ACCESS_DENIED
                            HttpStatusCode.NotFound -> ErrorType.NO_RESULT
                            HttpStatusCode.RequestTimeout -> ErrorType.TIMED_OUT
                            else -> ErrorType.UNKNOWN
                        }
                    )
                }
                is ServerResponseException -> HttpResult.Error(ErrorType.INTERNAL_ERROR)
                is SocketTimeoutException, is TimeoutCancellationException -> HttpResult.Error(ErrorType.TIMED_OUT)
                is IOException -> HttpResult.Error(ErrorType.NETWORK_ERROR)
                else -> HttpResult.Error(ErrorType.UNKNOWN)
            }
        }
    }

}