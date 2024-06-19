package com.hivian.kmp_mvvm.basicFeature.domain.usecases

import com.hivian.kmp_mvvm.basicFeature.data.mappers.ImageSize
import com.hivian.kmp_mvvm.basicFeature.domain.models.RandomUser
import com.hivian.kmp_mvvm.basicFeature.domain.repository.IRandomUsersRepository
import com.hivian.kmp_mvvm.core.datasources.ServiceResult

class GetRandomUserByIdUseCase(
    private val randomUsersService: IRandomUsersRepository
) {

    suspend operator fun invoke(userId: Int): ServiceResult<RandomUser> {
        return randomUsersService.getUserById(userId, ImageSize.LARGE)
    }

}