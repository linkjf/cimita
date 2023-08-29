package com.linkjf.climita.data.mapper

import com.linkjf.climita.remote.fakes.FakeRemoteData
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationMapperTest {

    private lateinit var mapper: LocationMapper

    @Before
    fun setUp() {
        mapper = LocationMapper()
    }

    @Test
    fun `given a LocationEntity when mapFromEntity should return Location`() =
        run { // Given

            // Given
            val responseModel = FakeRemoteData.getFakeLocationEntity()

            // When
            val result = mapper.mapFromEntity(responseModel)

            // Should
            assertEquals(result.country, "Colombia")
            assertEquals(result.region, "America")
            assertEquals(result.lon, 12.0)
        }

    @Test
    fun `given a Location when mapToEntity should return LocationEntity`() =
        run { // Given

            // Given
            val responseModel = FakeRemoteData.getFakeLocation()

            // When
            val result = mapper.mapToEntity(responseModel)

            // Should
            assertEquals(result.country, "Colombia")
            assertEquals(result.region, "America")
            assertEquals(result.lon, 12.0)
        }
}
