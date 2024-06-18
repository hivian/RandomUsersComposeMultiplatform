package com.hivian.kmp_mvvm.core.datasources.local.converters

import androidx.room.TypeConverter
import com.hivian.kmp_mvvm.core.datasources.models.Location
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LocationConverter {

    @TypeConverter
    fun locationToJson(value: Location): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun jsonToLocation(value: String): Location {
        return Json.decodeFromString(value)
    }
}
