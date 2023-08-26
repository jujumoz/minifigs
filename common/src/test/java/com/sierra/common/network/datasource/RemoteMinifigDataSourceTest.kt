package com.sierra.common.network.datasource

import com.sierra.common.domain.model.SOME_REMOTE_MINIFIG
import com.sierra.common.network.service.ApiService
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeTrue
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class RemoteMinifigDataSourceTest {

    private val service: ApiService = mock {
        onBlocking { getMinifigs() } doReturn listOf(SOME_REMOTE_MINIFIG)
    }

    private val underTest = RemoteMinifigDataSource(service)

    @Test
    fun `should get from api service when get minifig list`() = runTest {
        underTest.getMinifigList().isSuccess.shouldBeTrue()

        verify(service).getMinifigs()
    }
}
