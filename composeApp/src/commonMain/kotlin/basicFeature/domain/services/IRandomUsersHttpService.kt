package basicFeature.domain.services

import datasources.models.Results
import datasources.remote.HttpResult

interface IRandomUsersHttpService {

    suspend fun fetchRandomUsers(page: Int, results: Int): HttpResult<Results>

}
