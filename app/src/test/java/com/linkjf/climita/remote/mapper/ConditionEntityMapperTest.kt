package com.linkjf.climita.remote.mapper

import com.linkjf.climita.remote.fakes.FakeRemoteData
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ConditionEntityMapperTest {

    private lateinit var mapper: ConditionEntityMapper

    @Before
    fun setUp() {
        mapper = ConditionEntityMapper()
    }

    @Test
    fun `given a ConditionResponse when mapFromModel should return ConditionEntity `() =
        run { // Given

            // Given
            val responseModel = FakeRemoteData.getFakeConditionResponse()

            // When
            val result = mapper.mapFromModel(responseModel)

            // Should
            assertEquals(result.code, 1)
            assertEquals(result.icon, "icon")
            assertEquals(result.text, "Condition")
        }
}
