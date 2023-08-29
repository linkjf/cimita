package com.linkjf.climita.remote.mapper

import com.linkjf.climita.remote.fakes.FakeRemoteData
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationEntityMapperTest {

    private lateinit var mapper: LocationEntityMapper

    @Before
    fun setUp() {
        mapper = LocationEntityMapper()
    }

    @Test
    fun `given a LocationResponse when mapFromModel should return LocationEntity `() =
        run { // Given

            // Given
            val responseModel = FakeRemoteData.getFakeLocationResponse()

            // When
            val result = mapper.mapFromModel(responseModel)

            // Should
            TestCase.assertEquals(result.country, "Colombia")
            TestCase.assertEquals(result.region, "America")
            TestCase.assertEquals(result.lon, 12.0)
        }
}
