package basicFeature.data.sources

import basicFeature.domain.services.IRandomUsersHttpService
import datasources.models.Results
import datasources.remote.HttpResult
import datasources.remote.IHttpService
import servicelocator.IoC

internal class RandomUsersHttpService: IRandomUsersHttpService {

    private val httpService = IoC.resolve<IHttpService>()

    override suspend fun fetchRandomUsers(page: Int, results: Int): HttpResult<Results> {
        return safeApiCall {
            httpService.fetchRandomUsers(page = page, results = results)
        }
    }

}
