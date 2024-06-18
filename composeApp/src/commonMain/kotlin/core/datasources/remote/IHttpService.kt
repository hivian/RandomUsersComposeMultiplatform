package datasources.remote

import kotlin.reflect.KClass


interface IHttpService {

    suspend fun <T : Any> get(url: String, ofClass: KClass<T>): HttpResult<T>

    suspend fun <T : Any> safeApiCall(call: suspend () -> T) : HttpResult<T> {
        return try {
            val response = call.invoke()

            HttpResult.Success(response)
        } catch (exception: Exception) {
            when (exception) {
                is UnknownHostException -> HttpResult.Error(ErrorType.HOST_UNREACHABLE)
                is CancellationException -> HttpResult.Error(ErrorType.CANCELLED)
                is SocketTimeoutException -> HttpResult.Error(ErrorType.TIMED_OUT)
                is HttpException -> {
                    HttpResult.Error(
                        when (exception.code()) {
                            HttpStatusCode.unauthorized, HttpStatusCode.forbidden -> ErrorType.ACCESS_DENIED
                            HttpStatusCode.notFound -> ErrorType.NO_RESULT
                            HttpStatusCode.timedOut -> ErrorType.TIMED_OUT
                            else -> ErrorType.UNKNOWN
                        }
                    )
                }
                else -> HttpResult.Error(ErrorType.UNKNOWN)
            }
        }
    }

}