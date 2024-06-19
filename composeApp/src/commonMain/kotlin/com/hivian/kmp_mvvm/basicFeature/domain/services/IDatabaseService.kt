package com.hivian.kmp_mvvm.basicFeature.domain.services

import com.hivian.kmp_mvvm.core.datasources.models.RandomUserDTO

interface IDatabaseService {

    suspend fun getUserById(userId: Int): RandomUserDTO

    suspend fun fetchUsers(pageIndex: Int, pageSize: Int): List<RandomUserDTO>

    suspend fun upsertUsers(users : List<RandomUserDTO>)

}
