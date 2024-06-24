package com.hivian.randomusers.core.datasources.database.converters

import app.cash.sqldelight.ColumnAdapter
import com.hivian.randomusers.core.datasources.models.Picture
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object PictureAdapter: ColumnAdapter<Picture, String> {

    override fun decode(databaseValue: String): Picture {
        return Json.decodeFromString(databaseValue)
    }

    override fun encode(value: Picture): String {
        return Json.encodeToString(value)
    }

}
