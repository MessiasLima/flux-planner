package io.appoutlet.flux.common.core.database

import io.appoutlet.flux.common.core.database.generated.FluxDB
import io.appoutlet.flux.common.core.database.user.UserRepository
import org.koin.dsl.module

val databaseModule = module {
    single { FluxDB(get()) }
    factory { UserRepository(get()) }
}
