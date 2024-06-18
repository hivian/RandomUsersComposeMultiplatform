package basicFeature.domain.repository

import basicFeature.data.mappers.ImageSize
import com.hivian.compose_mvvm.core.datasources.ServiceResult
import basicFeature.domain.models.RandomUser

interface IRandomUsersRepository {

    suspend fun fetchRandomUsers(pageIndex: Int, pageSize: Int): ServiceResult<List<RandomUser>>

    suspend fun getUserById(userId: Int, imageSize: ImageSize): ServiceResult<RandomUser>

}
