package datasources.local

import androidx.room.BuiltInTypeConverters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import datasources.local.converters.LocationConverter
import datasources.local.converters.NameConverter
import datasources.local.converters.PictureConverter
import datasources.local.dao.IRandomUsersDao
import com.hivian.kmp_mvvm.datasources.models.RandomUserDTO

@Database(entities = [RandomUserDTO::class], version = AppDatabase.DB_VERSION, exportSchema = false)
@TypeConverters(NameConverter::class, LocationConverter::class, PictureConverter::class, builtInTypeConverters = BuiltInTypeConverters())
abstract class AppDatabase : RoomDatabase(){

    abstract fun randomUsersDao() : IRandomUsersDao

    companion object {
        const val DB_NAME = "app_database"

        const val DB_VERSION = 1
    }

}


