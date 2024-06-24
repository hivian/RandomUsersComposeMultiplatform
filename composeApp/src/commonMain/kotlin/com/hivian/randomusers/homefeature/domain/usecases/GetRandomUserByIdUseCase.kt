package com.hivian.randomusers.homefeature.domain.usecases

import com.hivian.randomusers.core.datasources.ServiceResult
import com.hivian.randomusers.homefeature.data.mappers.ImageSize
import com.hivian.randomusers.homefeature.domain.models.RandomUser
import com.hivian.randomusers.homefeature.domain.services.IRandomUsersService

class GetRandomUserByIdUseCase(
    private val randomUsersService: IRandomUsersService
) {

    operator fun invoke(userId: Int): ServiceResult<RandomUser> {
        return randomUsersService.getUserById(userId, ImageSize.LARGE)
    }

}