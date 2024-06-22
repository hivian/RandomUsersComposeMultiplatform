package com.hivian.kmp_mvvm.core.datasources.database

import app.cash.sqldelight.db.SqlDriver

const val DB_NAME = "AppDatabase"

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
} 