package com.erolgizlice.satellite.core.domain

import com.erolgizlice.satellite.core.data.repository.SatelliteRepository
import com.erolgizlice.satellite.core.model.data.SatelliteDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSatelliteDetailUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) {

    operator fun invoke(satelliteId: Int): Flow<SatelliteDetail> =
        satelliteRepository.getSatelliteDetail(satelliteId)
}