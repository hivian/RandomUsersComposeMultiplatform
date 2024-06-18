package basicFeature.data.sources

import datasources.models.RandomUserDTO
import basicFeature.domain.services.IRandomUsersDatabaseService
import datasources.local.AppDatabase
import servicelocator.IoC

internal class RandomUsersDatabaseService: IRandomUsersDatabaseService {

    private val databaseService = IoC.resolve<AppDatabase>()

    override suspend fun getUserById(userId: Int): RandomUserDTO {
        return databaseService.randomUsersDao().getRandomUserById(userId)
    }

    override suspend fun fetchUsers(pageIndex: Int, pageSize: Int): List<RandomUserDTO> {
        return databaseService.randomUsersDao().getAllRandomUsers((pageIndex - 1) * pageSize, pageSize)
    }

    override suspend fun upsertUsers(users: List<RandomUserDTO>) {
        databaseService.randomUsersDao().upsert(users)
    }

}
