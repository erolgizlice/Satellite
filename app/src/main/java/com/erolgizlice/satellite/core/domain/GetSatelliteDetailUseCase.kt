package com.erolgizlice.satellite.core.domain

import com.erolgizlice.satellite.core.data.repository.SatelliteRepository
import com.erolgizlice.satellite.core.model.data.SatelliteDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetSatelliteDetailUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) {

    operator fun invoke(satelliteId: Int): Flow<SatelliteDetail> =
        combine(
            satelliteRepository.getSatelliteDetail(satelliteId),
            satelliteRepository.getSatellitePosition(satelliteId)
        ) { satelliteDetail, position ->
            SatelliteDetail(
                id = satelliteDetail.id,
                costPerLaunch = satelliteDetail.costPerLaunch,
                firstFlight = satelliteDetail.firstFlight,
                height = satelliteDetail.height,
                mass = satelliteDetail.mass,
                position = position
            )
        }
}