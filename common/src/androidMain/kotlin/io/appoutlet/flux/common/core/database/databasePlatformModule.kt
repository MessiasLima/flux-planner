package io.appoutlet.flux.common.core.database

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.dsl.module

actual val databasePlatformModule = module {
    single<SqlDriver> { AndroidSqliteDriver(FluxDB.Schema, get()) }
}
