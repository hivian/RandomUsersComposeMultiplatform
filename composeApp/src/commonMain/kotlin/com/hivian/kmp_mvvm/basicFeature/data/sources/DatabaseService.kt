package com.hivian.kmp_mvvm.basicFeature.data.sources

import com.hivian.kmp_mvvm.basicFeature.domain.services.IDatabaseService
import com.hivian.kmp_mvvm.core.datasources.models.RandomUserDTO
import com.hivian.kmp_mvvm.core.datasources.database.AppDatabase

internal class DatabaseService(
    private val databaseService: AppDatabase
): IDatabaseService {

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
