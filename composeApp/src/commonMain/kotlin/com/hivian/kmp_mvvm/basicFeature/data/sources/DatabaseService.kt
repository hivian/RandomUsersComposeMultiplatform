package com.hivian.kmp_mvvm.basicFeature.data.sources

import androidx.room.Transaction
import com.hivian.kmp_mvvm.basicFeature.domain.services.IDatabaseService
import com.hivian.kmp_mvvm.core.datasources.models.RandomUserDTO
import com.hivian.kmp_mvvm.core.datasources.database.SQLDelightDatabase
import com.hivian.kmp_mvvm.core.datasources.models.Name
import com.hivian.kmpmvvm.core.datasources.database.RandomUserEntity
import kotlinx.coroutines.CoroutineDispatcher

internal class DatabaseService(
    private val databaseService: SQLDelightDatabase,
    private val dispatcher: CoroutineDispatcher
): IDatabaseService {

    override fun getUserById(userId: Int): RandomUserDTO {
        return databaseService { query ->
            query.getRandomUserById(userId)
                .executeAsOne()
                .let { mapRandomUser(it) }
        }
    }

    override fun getAllUsers(pageIndex: Int, pageSize: Int): List<RandomUserDTO> {
        return databaseService { query ->
            query.getAllRandomUsers(((pageIndex - 1) * pageSize).toLong(), pageSize.toLong())
                .executeAsList()
                .map { mapRandomUser(it) }
        }
    }

    override fun getUserByName(name: Name): RandomUserDTO? {
        return databaseService { query ->
            query.getRandomUserByServerId(name)
                .executeAsOneOrNull()
                ?.let { mapRandomUser(it) }
        }
    }

    override  fun upsertUsers(users: List<RandomUserDTO>) {
        databaseService { query ->
            query.transaction {
                query.getRandomUserByServerId(users.map { it.name })
            }
        }
        /*databaseService { query ->
            query.deleteAll()
        }
        withContext(dispatcher) {
            databaseService.randomUsersDao().upsert(users)
        }*/
    }

    @Transaction
    suspend fun upsert(randomUser: RandomUserDTO) {
        val isRandomUser = getRandomUserByServerId(randomUser.name)
        isRandomUser?.let {
            update(randomUser.apply {
                localId = isRandomUser.localId
            })
        } ?: run {
            insert(randomUser)
        }
    }

    suspend fun upsert(randomUsersList: List<RandomUserDTO>) {
        randomUsersList.forEach { upsert(it) }
    }

    override fun deleteAll() {
        databaseService { query -> query.deleteAll() }
    }

    private fun mapRandomUser(randomUserEntity: RandomUserEntity) : RandomUserDTO {
        return RandomUserDTO(
            localId = randomUserEntity.localId,
            gender = randomUserEntity.gender,
            name = randomUserEntity.name,
            location = randomUserEntity.location,
            email = randomUserEntity.email,
            phone = randomUserEntity.phone,
            cell = randomUserEntity.cell,
            picture = randomUserEntity.picture
        )
    }

}
