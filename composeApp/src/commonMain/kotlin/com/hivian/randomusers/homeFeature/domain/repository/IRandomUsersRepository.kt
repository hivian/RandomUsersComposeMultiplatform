package com.hivian.randomusers.homeFeature.domain.repository

import com.hivian.randomusers.core.datasources.ServiceResult
import com.hivian.randomusers.homeFeature.data.mappers.ImageSize
import com.hivian.randomusers.homeFeature.domain.models.RandomUser

interface IRandomUsersRepository {

    suspend fun fetchRandomUsers(pageIndex: Int, pageSize: Int): ServiceResult<List<RandomUser>>

    fun getUserById(userId: Int, imageSize: ImageSize): ServiceResult<RandomUser>

}
