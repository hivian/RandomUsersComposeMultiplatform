package basicFeature.data.repository

import com.hivian.compose_mvvm.core.datasources.ServiceResult
import basicFeature.data.mappers.ImageSize
import basicFeature.data.mappers.mapToRandomUser
import basicFeature.data.mappers.mapToRandomUsers
import datasources.remote.ErrorType
import datasources.remote.HttpResult
import basicFeature.domain.models.RandomUser
import basicFeature.domain.repository.IRandomUsersRepository
import basicFeature.domain.services.IRandomUsersDatabaseService
import basicFeature.domain.services.IRandomUsersHttpService
import servicelocator.IoC

internal class RandomUsersRepository: IRandomUsersRepository {

    private val randomUsersDatabaseService = IoC.resolve<IRandomUsersDatabaseService>()

    private val randomUsersHttpService = IoC.resolve<IRandomUsersHttpService>()

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
