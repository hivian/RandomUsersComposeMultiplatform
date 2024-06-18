package com.hivian.kmp_mvvm.basicFeature.data.repository

import com.hivian.kmp_mvvm.core.datasources.ServiceResult
import com.hivian.kmp_mvvm.basicFeature.data.mappers.ImageSize
import com.hivian.kmp_mvvm.basicFeature.data.mappers.mapToRandomUser
import com.hivian.kmp_mvvm.basicFeature.data.mappers.mapToRandomUsers
import com.hivian.kmp_mvvm.basicFeature.domain.models.RandomUser
import com.hivian.kmp_mvvm.basicFeature.domain.repository.IRandomUsersRepository
import com.hivian.kmp_mvvm.basicFeature.domain.services.IApiService
import com.hivian.kmp_mvvm.basicFeature.domain.services.IRandomUsersDatabaseService
import com.hivian.kmp_mvvm.core.datasources.remote.ErrorType
import com.hivian.kmp_mvvm.core.datasources.remote.HttpResult

internal class RandomUsersRepository(
    private val randomUsersDatabaseService: IRandomUsersDatabaseService,
    private val randomUsersHttpService: IApiService
): IRandomUsersRepository {

    override suspend fun getUserById(userId: Int, imageSize: ImageSize): ServiceResult<RandomUser> {
        return runCatching {
            ServiceResult.Success(randomUsersDatabaseService.getUserById(userId).mapToRandomUser(imageSize))
        }.getOrDefault(
            ServiceResult.Error(ErrorType.DATABASE_ERROR)
        )
    }

    override suspend fun fetchRandomUsers(pageIndex: Int, pageSize: Int): ServiceResult<List<RandomUser>> {
        val httpUsersResult = randomUsersHttpService.fetchRandomUsers(pageIndex, pageSize)
        val databaseUserResult = fetchDatabaseUsers(pageIndex, pageSize)

        return when (httpUsersResult) {
            is HttpResult.Success -> {
                val users = httpUsersResult.data.results

                if (users.isEmpty()) {
                    ServiceResult.Error(ErrorType.NO_RESULT, databaseUserResult)
                } else {
                    randomUsersDatabaseService.upsertUsers(users)
                    ServiceResult.Success(fetchDatabaseUsers(pageIndex, pageSize))
                }
            }
            is HttpResult.Error -> {
                ServiceResult.Error(httpUsersResult.errorType, databaseUserResult)
            }
        }
    }

    private suspend fun fetchDatabaseUsers(pageIndex: Int, pageSize: Int): List<RandomUser> {
        return randomUsersDatabaseService
            .fetchUsers(pageIndex, pageSize)
            .mapToRandomUsers(ImageSize.MEDIUM)
    }

}
