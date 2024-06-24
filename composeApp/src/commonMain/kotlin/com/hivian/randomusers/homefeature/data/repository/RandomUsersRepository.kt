package com.hivian.randomusers.homefeature.data.repository

import com.hivian.randomusers.core.datasources.ServiceResult
import com.hivian.randomusers.core.datasources.remote.ErrorType
import com.hivian.randomusers.core.datasources.remote.HttpResult
import com.hivian.randomusers.core.domain.services.IApiService
import com.hivian.randomusers.core.domain.services.IDatabaseService
import com.hivian.randomusers.homefeature.data.mappers.ImageSize
import com.hivian.randomusers.homefeature.data.mappers.mapToRandomUser
import com.hivian.randomusers.homefeature.data.mappers.mapToRandomUsers
import com.hivian.randomusers.homefeature.domain.models.RandomUser
import com.hivian.randomusers.homefeature.domain.repository.IRandomUsersRepository

internal class RandomUsersRepository(
    private val databaseService: IDatabaseService,
    private val httpService: IApiService
): IRandomUsersRepository {

    override suspend fun fetchRandomUsers(pageIndex: Int, pageSize: Int): ServiceResult<List<RandomUser>> {
        val httpUsersResult = httpService.fetchRandomUsers(pageIndex, pageSize)
        val databaseUserResult = fetchDatabaseUsers(pageIndex, pageSize)

        return when (httpUsersResult) {
            is HttpResult.Success -> {
                val users = httpUsersResult.data.results

                if (users.isEmpty()) {
                    ServiceResult.Error(ErrorType.NO_RESULT, databaseUserResult)
                } else {
                    databaseService.upsertUsers(users)
                    ServiceResult.Success(fetchDatabaseUsers(pageIndex, pageSize))
                }
            }
            is HttpResult.Error -> {
                ServiceResult.Error(httpUsersResult.errorType, databaseUserResult)
            }
        }
    }

    override fun getUserById(userId: Int, imageSize: ImageSize): ServiceResult<RandomUser> {
        return runCatching {
            ServiceResult.Success(databaseService.getUserById(userId).mapToRandomUser(imageSize))
        }.getOrDefault(
            ServiceResult.Error(ErrorType.DATABASE_ERROR)
        )
    }

    private fun fetchDatabaseUsers(pageIndex: Int, pageSize: Int): List<RandomUser> {
        return databaseService
            .getAllUsers(pageIndex, pageSize)
            .mapToRandomUsers(ImageSize.MEDIUM)
    }

}
