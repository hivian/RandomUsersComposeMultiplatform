package com.hivian.randomusers.core.datasources.database.converters

import app.cash.sqldelight.ColumnAdapter
import com.hivian.randomusers.core.datasources.models.Location
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object LocationAdapter: ColumnAdapter<Location, String> {

    override fun decode(databaseValue: String): Location {
        return Json.decodeFromString(databaseValue)
    }

    override fun encode(value: Location): String {
        return Json.encodeToString(value)
    }

}
