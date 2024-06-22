package com.hivian.kmp_mvvm.core.datasources.database

import app.cash.sqldelight.adapter.primitive.IntColumnAdapter
import com.hivian.kmp_mvvm.core.datasources.database.converters.LocationAdapter
import com.hivian.kmp_mvvm.core.datasources.database.converters.NameAdapter
import com.hivian.kmp_mvvm.core.datasources.database.converters.PictureAdapter
import com.hivian.kmpmvvm.core.datasources.database.AppDatabaseQueries
import com.hivian.kmpmvvm.core.datasources.database.RandomUserEntity

internal class SQLDelightDatabase(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = AppDatabase(
        driver = databaseDriverFactory.createDriver(),
        randomUserEntityAdapter = RandomUserEntity.Adapter(
            localIdAdapter = IntColumnAdapter,
            nameAdapter = NameAdapter,
            locationAdapter = LocationAdapter,
            pictureAdapter = PictureAdapter
        )
    )

    operator fun <T> invoke(block: (AppDatabaseQueries) -> T): T {
        return block(database.appDatabaseQueries)
    }

}
