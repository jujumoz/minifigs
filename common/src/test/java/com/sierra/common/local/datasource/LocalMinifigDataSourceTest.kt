package com.sierra.common.local.datasource

import com.sierra.common.domain.model.SOME_LOCAL_MINIFIG
import com.sierra.common.domain.model.SOME_MINIFIG
import com.sierra.common.local.dao.MinifigDao
import com.sierra.common.local.db.AppDatabase
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeTrue
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.anyVararg
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class LocalMinifigDataSourceTest {

    private val appDatabase: AppDatabase = mock()
    private val dao: MinifigDao = mock {
        onBlocking { getById(any()) } doReturn SOME_LOCAL_MINIFIG
        onBlocking { getAll() } doReturn flowOf(listOf(SOME_LOCAL_MINIFIG))
    }

    private val underTest = LocalMinifigDataSource(
        appDatabase = appDatabase,
        minifigDao = dao,
    )

    @Test
    fun `should get from dao when get minifig by id`() = runTest {
        val id = "id"

        underTest.getMinifigById(id).isSuccess.shouldBeTrue()

        verify(dao).getById(id)
    }

    @Test
    fun `should get from dao when get minifig list`() = runTest {
        underTest.getMinifigList().count() shouldBeEqualTo 1

        verify(dao).getAll()
    }

    @Test
    fun `should insert with dao when save minifig list`() = runTest {
        underTest.saveMinifigList(listOf(SOME_MINIFIG)).isSuccess.shouldBeTrue()

        verify(dao).insertAll(anyVararg())
    }
}
