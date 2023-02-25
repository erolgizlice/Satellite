package com.erolgizlice.satellite.core.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val satelliteDispatcher: SatelliteDispatchers)

enum class SatelliteDispatchers {
    IO
}