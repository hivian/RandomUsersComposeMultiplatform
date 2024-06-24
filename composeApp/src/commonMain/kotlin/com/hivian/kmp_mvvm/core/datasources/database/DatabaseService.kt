package com.hivian.kmp_mvvm.core.datasources.database

import app.cash.sqldelight.adapter.primitive.IntColumnAdapter
import com.hivian.kmp_mvvm.core.datasources.database.converters.LocationAdapter
import com.hivian.kmp_mvvm.core.datasources.database.converters.NameAdapter
import com.hivian.kmp_mvvm.core.datasources.database.converters.PictureAdapter
import com.hivian.kmp_mvvm.core.datasources.models.Name
import com.hivian.kmp_mvvm.core.datasources.models.RandomUserDTO
import com.hivian.kmp_mvvm.core.domain.services.IDatabaseService
import com.hivian.kmp_mvvm.homeFeature.data.mappers.mapToRandomUserDTO
import com.hivian.kmp_mvvm.homeFeature.data.mappers.mapToRandomUserEntity
import com.hivian.kmp_mvvm.homeFeature.data.mappers.mapToRandomUsersDTO
import com.hivian.kmpmvvm.core.datasources.database.RandomUserEntity

internal class DatabaseService(
    databaseDriverFactory: DatabaseDriverFactory,
): IDatabaseService {

    private val database = AppDatabase(
        driver = databaseDriverFactory.createDriver(),
        randomUserEntityAdapter = RandomUserEntity.Adapter(
            localIdAdapter = IntColumnAdapter,
            nameAdapter = NameAdapter,
            locationAdapter = LocationAdapter,
            pictureAdapter = PictureAdapter
        )
    )

    private val queries = database.appDatabaseQueries

    override fun getUserById(userId: Int): RandomUserDTO {
        return queries.getRandomUserById(userId)
            .executeAsOne()
            .mapToRandomUserDTO()
    }

    override fun getAllUsers(pageIndex: Int, pageSize: Int): List<RandomUserDTO> {
        return queries.getAllRandomUsers(
            index = ((pageIndex - 1) * pageSize).toLong(),
            limit = pageSize.toLong()
        )
            .executeAsList()
            .mapToRandomUsersDTO()
    }

    override fun getUserByName(name: Name): RandomUserDTO? {
        return queries.getRandomUserByName(name)
            .executeAsOneOrNull()
            ?.mapToRandomUserDTO()
    }

    override fun upsertUsers(users: List<RandomUserDTO>) {
        queries.transaction {
            users.forEach { upsertUser(it) }
        }
    }

    private fun upsertUser(randomUser: RandomUserDTO) {
        queries.apply {
            getRandomUserByName(randomUser.name).executeAsOneOrNull()?.let { user ->
                upsert(randomUser.apply { localId = user.localId }.mapToRandomUserEntity())
            } ?: insert(randomUser.mapToRandomUserEntity())
        }
    }

    override fun deleteAll() {
        queries.deleteAll()
    }

}
