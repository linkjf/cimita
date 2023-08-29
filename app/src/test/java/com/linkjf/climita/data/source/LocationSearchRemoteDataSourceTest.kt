package com.linkjf.climita.data.source

import com.linkjf.climita.data.models.LocationEntity
import com.linkjf.climita.data.repository.LocationSearchDataSource
import com.linkjf.climita.data.repository.LocationSearchRemote
import com.linkjf.climita.remote.common.Result
import com.linkjf.climita.remote.common.Result.Error
import com.linkjf.climita.remote.common.Result.Success
import com.linkjf.climita.remote.fakes.FakeRemoteData
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn


@RunWith(MockitoJUnitRunner::class)
class LocationSearchRemoteDataSourceTest {

    @Mock
    lateinit var locationRemote: LocationSearchRemote

    private lateinit var locationSearchDataSource: LocationSearchDataSource

    @Before
    fun setUp() {
        locationSearchDataSource = LocationSearchRemoteDataSource(
            locationRemote
        )
    }

    @Test
    fun `getLocations whit a valid query should return a list with 2 LocationResponse`() =
        runBlocking {

            // Given
            val fakeResponse = FakeRemoteData.getFakeLocationEntityList()
            `when`(locationRemote.getLocations(any())) doReturn Success(
                fakeResponse
            )

            //When
            val result = locationSearchDataSource.getLocations(FakeRemoteData.fakeQuery)

            // Then
            assertThat(result, instanceOf(Success::class.java))
            assertEquals((result as Success).data.size, 2)
        }
}
