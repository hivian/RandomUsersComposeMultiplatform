package com.hivian.randomusers.core.datasources.database.converters

import app.cash.sqldelight.ColumnAdapter
import com.hivian.randomusers.core.datasources.models.Name
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object NameAdapter: ColumnAdapter<Name, String> {

    override fun decode(databaseValue: String): Name {
        return Json.decodeFromString(databaseValue)
    }

    override fun encode(value: Name): String {
        return Json.encodeToString(value)
    }

}
