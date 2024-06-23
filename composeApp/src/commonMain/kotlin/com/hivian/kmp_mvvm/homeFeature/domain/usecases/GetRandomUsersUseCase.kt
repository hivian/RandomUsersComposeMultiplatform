package com.hivian.kmp_mvvm.homeFeature.domain.usecases

import com.hivian.kmp_mvvm.homeFeature.domain.models.RandomUser
import com.hivian.kmp_mvvm.homeFeature.domain.repository.IRandomUsersRepository
import com.hivian.kmp_mvvm.core.datasources.ServiceResult

class GetRandomUsersUseCase(
    private val randomUsersService: IRandomUsersRepository
) {

    suspend operator fun invoke(pageIndex: Int, pageSize: Int): ServiceResult<List<RandomUser>> {
        return randomUsersService.fetchRandomUsers(pageIndex, pageSize)
    }

}