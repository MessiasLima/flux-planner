package io.appoutlet.flux.common.core.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import io.appoutlet.flux.common.core.database.generated.FluxDB
import org.koin.dsl.module
import java.io.File

// private const val DATABASE_PATH = "jdbc:sqlite:HOME/.config/flux/data/flux.db"

actual val databasePlatformModule = module {
    single<SqlDriver> {
        val userHome = System.getProperty("user.home")
        val databasePath = "$userHome/.config/flux/database"

        File(databasePath).mkdirs()

        val driver = JdbcSqliteDriver("jdbc:sqlite:$databasePath/flux.db")
        FluxDB.Schema.create(driver)
        driver
    }
}
