package com.hivian.randomusers.core.datasources.database

import app.cash.sqldelight.db.SqlDriver

const val DB_NAME = "AppDatabase"

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
} 