package com.hivian.randomusers.homeFeature.domain.usecases

import com.hivian.randomusers.core.datasources.ServiceResult
import com.hivian.randomusers.homeFeature.domain.models.RandomUser
import com.hivian.randomusers.homeFeature.domain.repository.IRandomUsersRepository

class GetRandomUsersUseCase(
    private val randomUsersService: IRandomUsersRepository
) {

    suspend operator fun invoke(pageIndex: Int, pageSize: Int): ServiceResult<List<RandomUser>> {
        return randomUsersService.fetchRandomUsers(pageIndex, pageSize)
    }

}