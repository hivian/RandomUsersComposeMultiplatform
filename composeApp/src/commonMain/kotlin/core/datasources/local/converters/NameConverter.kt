package datasources.local.converters

import androidx.room.TypeConverter
import datasources.models.Name
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class NameConverter {

    @TypeConverter
    fun nameToJson(value: Name): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun jsonToName(value: String): Name {
        return Json.decodeFromString(value)
    }
}
