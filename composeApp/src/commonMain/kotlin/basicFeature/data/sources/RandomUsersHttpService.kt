package basicFeature.data.sources

import basicFeature.domain.services.IRandomUsersHttpService
import datasources.models.Results
import datasources.remote.HttpResult
import core.datasources.remote.IHttpClient
import core.datasources.remote.get
import servicelocator.IoC

internal class RandomUsersHttpService: IRandomUsersHttpService {

    companion object {
        private const val URL_PATH = "api/1.3"
        private const val SEED_PARAMETER = "seed"
        private const val PAGE_PARAMETER = "page"
        private const val RESULTS_PARAMETER = "results"
    }

    private val httpClient = IoC.resolve<IHttpClient>()

    override suspend fun fetchRandomUsers(page: Int, results: Int): HttpResult<Results> {
        return httpClient.get(
            urlPath = URL_PATH,
            parameters = mapOf(
                SEED_PARAMETER to "easypeasy",
                PAGE_PARAMETER to page.toString(),
                RESULTS_PARAMETER to results.toString()
            )
        )
    }

}
