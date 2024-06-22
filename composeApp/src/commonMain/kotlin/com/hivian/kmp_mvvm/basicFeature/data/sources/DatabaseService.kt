package com.hivian.kmp_mvvm.basicFeature.data.sources

import com.hivian.kmp_mvvm.basicFeature.domain.services.IDatabaseService
import com.hivian.kmp_mvvm.core.datasources.models.RandomUserDTO
import com.hivian.kmp_mvvm.core.datasources.database.AppDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class DatabaseService(
    private val databaseService: AppDatabase,
    private val dispatcher: CoroutineDispatcher
): IDatabaseService {

    override suspend fun getUserById(userId: Int): RandomUserDTO {
        return withContext(dispatcher) {
            databaseService.randomUsersDao().getRandomUserById(userId)
        }
    }

    override suspend fun fetchUsers(pageIndex: Int, pageSize: Int): List<RandomUserDTO> {
        return withContext(dispatcher) {
            databaseService.randomUsersDao().getAllRandomUsers((pageIndex - 1) * pageSize, pageSize)
        }
    }

    override suspend fun upsertUsers(users: List<RandomUserDTO>) {
        withContext(dispatcher) {
            databaseService.randomUsersDao().upsert(users)
        }
    }

}
