package core.datasources

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import datasources.local.AppDatabase
import platform.Foundation.NSHomeDirectory
import datasources.local.instantiateImpl

fun getAppDatabase(): AppDatabase {
    val dbFile = NSHomeDirectory() + "/${AppDatabase.DB_NAME}"

    return Room.databaseBuilder<AppDatabase>(
        name = dbFile,
        factory = { AppDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}
