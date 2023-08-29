package com.linkjf.climita.remote.repository

import com.linkjf.climita.data.repository.LocationSearchRemote
import com.linkjf.climita.remote.api.LocationSearchService
import com.linkjf.climita.remote.common.Result.Error
import com.linkjf.climita.remote.common.Result.Success
import com.linkjf.climita.remote.fakes.FakeRemoteData
import com.linkjf.climita.remote.mapper.LocationEntityMapper
import com.linkjf.climita.remote.models.LocationSearchResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import retrofit2.Response
import retrofit2.Response.*

@RunWith(MockitoJUnitRunner::class)
class LocationSearchRemoteImpTest {

    @Mock
    lateinit var locationSearchService: LocationSearchService

    @Mock
    lateinit var locationEntityMapper: LocationEntityMapper

    @Mock
    lateinit var responseError: Response<LocationSearchResponse>

    private lateinit var locationSearchRemote: LocationSearchRemote

    @Before
    fun setUp() {
        locationSearchRemote = LocationSearchRemoteImp(
            locationSearchService,
            locationEntityMapper
        )
    }

    @Test
    fun `getLocations whit a valid query should return a list of LocationEntity with 2 LocationEntity`() =
        runBlocking {

            // Given
            val fakeResponse = FakeRemoteData.getFakeLocationSearchResponse()
            val fakeEntity = FakeRemoteData.getFakeLocationEntity()
            `when`(locationSearchService.searchLocation(any())) doReturn success(fakeResponse)
            `when`(locationEntityMapper.mapFromModel(any())) doReturn fakeEntity

            //When
            val result =
                locationSearchRemote.getLocations(FakeRemoteData.fakeQuery)

            // Then
            assertThat(result, instanceOf(Success::class.java))
            assertEquals((result as Success).data.size, 2)
        }

    @Test
    fun `getLocations whit an invalid query should return a Error response`() =
        runBlocking {

            // Given
            val fakeResponse = FakeRemoteData.getFakeLocationSearchResponse()
            val fakeEntity = FakeRemoteData.getFakeLocationEntity()

            `when`(locationSearchService.searchLocation(any())) doReturn responseError

            //When
            val result =
                locationSearchRemote.getLocations(FakeRemoteData.fakeQuery)

            // Then
            assertThat(result, instanceOf(Error::class.java))
            assertEquals((result as Error).code, 0)

        }
}
