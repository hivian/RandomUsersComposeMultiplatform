package com.hivian.kmp_mvvm.homeFeature.domain.repository

import com.hivian.kmp_mvvm.core.datasources.ServiceResult
import com.hivian.kmp_mvvm.homeFeature.data.mappers.ImageSize
import com.hivian.kmp_mvvm.homeFeature.domain.models.RandomUser

interface IRandomUsersRepository {

    suspend fun fetchRandomUsers(pageIndex: Int, pageSize: Int): ServiceResult<List<RandomUser>>

    fun getUserById(userId: Int, imageSize: ImageSize): ServiceResult<RandomUser>

}
