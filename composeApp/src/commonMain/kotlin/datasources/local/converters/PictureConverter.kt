package datasources.local.converters

import androidx.room.TypeConverter
import datasources.models.Picture
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PictureConverter {

    @TypeConverter
    fun pictureToJson(value: Picture): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun jsonToPicture(value: String): Picture {
        return Json.decodeFromString(value)
    }
}
