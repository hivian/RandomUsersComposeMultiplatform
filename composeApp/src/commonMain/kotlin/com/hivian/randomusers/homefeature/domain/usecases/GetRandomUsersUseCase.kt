package com.hivian.randomusers.homefeature.domain.usecases

import com.hivian.randomusers.core.datasources.ServiceResult
import com.hivian.randomusers.homefeature.domain.models.RandomUser
import com.hivian.randomusers.homefeature.domain.repository.IRandomUsersRepository

class GetRandomUsersUseCase(
    private val randomUsersService: IRandomUsersRepository
) {

    suspend operator fun invoke(pageIndex: Int, pageSize: Int): ServiceResult<List<RandomUser>> {
        return randomUsersService.fetchRandomUsers(pageIndex, pageSize)
    }

}