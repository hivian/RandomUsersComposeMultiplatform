import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.hivian.kmp_mvvm.core.datasources.database.AppDatabase
import com.hivian.kmp_mvvm.core.datasources.database.DB_NAME

actual class DatabaseDriverFactory {

    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = AppDatabase.Schema,
            name = "$DB_NAME.db"
        )
    }

}