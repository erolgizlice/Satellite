package com.erolgizlice.satellite.core.domain

import com.erolgizlice.satellite.core.data.repository.InterfaceSatelliteRepository
import com.erolgizlice.satellite.core.model.data.Satellite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSatelliteListUseCase @Inject constructor(
    private val satelliteRepository: InterfaceSatelliteRepository
) {

    operator fun invoke(): Flow<List<Satellite>> = satelliteRepository.getSatelliteList()
}