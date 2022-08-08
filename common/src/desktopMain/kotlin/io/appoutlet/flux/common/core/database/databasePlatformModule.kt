package io.appoutlet.flux.common.core.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import org.koin.dsl.module

// private const val DATABASE_PATH = "jdbc:sqlite:HOME/.config/flux/data/flux.db"

actual val databasePlatformModule = module {
    single<SqlDriver> {
        val driver = JdbcSqliteDriver("jdbc:sqlite:/home/messias/Workspace/flux.db")
        FluxDB.Schema.create(driver)
        driver
    }
}
