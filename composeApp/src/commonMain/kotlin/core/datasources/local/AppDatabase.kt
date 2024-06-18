package datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import datasources.local.converters.LocationConverter
import datasources.local.converters.NameConverter
import datasources.local.converters.PictureConverter
import datasources.local.dao.IRandomUsersDao
import datasources.models.RandomUserDTO

@Database(entities = [RandomUserDTO::class], version = AppDatabase.DB_VERSION, exportSchema = false)
@TypeConverters(NameConverter::class, LocationConverter::class, PictureConverter::class)
abstract class AppDatabase : RoomDatabase(), DB {

    abstract fun randomUsersDao() : IRandomUsersDao

    companion object {
        const val DB_NAME = "app_database"

        const val DB_VERSION = 1
    }

    override fun clearAllTables() {
        super.clearAllTables()
    }

}

// FIXME: Added a hack to resolve below issue:
// Class 'AppDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables() {}
}
