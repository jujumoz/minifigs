package com.sierra.common.repository

import com.sierra.common.domain.model.SOME_MINIFIG
import com.sierra.common.local.datasource.LocalMinifigDataSource
import com.sierra.common.network.datasource.RemoteMinifigDataSource
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class MinifigRepositoryImplTest {

    private val minifigs = listOf(SOME_MINIFIG)

    private val remoteMinifigDataSource: RemoteMinifigDataSource = mock {
        onBlocking { getMinifigList() } doReturn Result.success(minifigs)
    }
    private val localMinifigDataSource: LocalMinifigDataSource = mock()

    private val underTest = MinifigRepositoryImpl(
        remoteMinifigDataSource = remoteMinifigDataSource,
        localMinifigDataSource = localMinifigDataSource,
    )

    @Test
    fun `should save to local source when get minifigs from remote source`() = runTest {
        underTest.updateMinifigList()

        verify(remoteMinifigDataSource).getMinifigList()
        verify(localMinifigDataSource).saveMinifigList(minifigs)
    }

    @Test
    fun `should get from local data source when get minifig list`() = runTest {
        underTest.getMinifigList()

        verify(localMinifigDataSource).getMinifigList()
    }

    @Test
    fun `should get from local data source when get minifig`() = runTest {
        val id = "id"

        underTest.getMinifigById(id)

        verify(localMinifigDataSource).getMinifigById(id)
    }
}
