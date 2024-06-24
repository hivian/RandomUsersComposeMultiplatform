package com.hivian.randomusers.homefeature.domain.repository

import com.hivian.randomusers.core.datasources.ServiceResult
import com.hivian.randomusers.homefeature.data.mappers.ImageSize
import com.hivian.randomusers.homefeature.domain.models.RandomUser

interface IRandomUsersRepository {

    suspend fun fetchRandomUsers(pageIndex: Int, pageSize: Int): ServiceResult<List<RandomUser>>

    fun getUserById(userId: Int, imageSize: ImageSize): ServiceResult<RandomUser>

}
