package com.hivian.randomusers.homeFeature.domain.usecases

import com.hivian.randomusers.core.datasources.ServiceResult
import com.hivian.randomusers.homeFeature.data.mappers.ImageSize
import com.hivian.randomusers.homeFeature.domain.models.RandomUser
import com.hivian.randomusers.homeFeature.domain.repository.IRandomUsersRepository

class GetRandomUserByIdUseCase(
    private val randomUsersService: IRandomUsersRepository
) {

    suspend operator fun invoke(userId: Int): ServiceResult<RandomUser> {
        return randomUsersService.getUserById(userId, ImageSize.LARGE)
    }

}