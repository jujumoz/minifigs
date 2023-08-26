package com.sierra.common.local.datasource

import com.sierra.common.domain.model.SOME_CATEGORY
import com.sierra.common.domain.model.SOME_LOCAL_CATEGORY
import com.sierra.common.local.dao.CategoryDao
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

internal class LocalCategoryDataSourceTest {

    private val appDatabase: AppDatabase = mock()
    private val dao: CategoryDao = mock {
        onBlocking { getById(any()) } doReturn SOME_LOCAL_CATEGORY
        onBlocking { getAll() } doReturn flowOf(listOf(SOME_LOCAL_CATEGORY))
    }

    private val underTest = LocalCategoryDataSource(
        appDatabase = appDatabase,
        categoryDao = dao,
    )

    @Test
    fun `should get from dao when get category by id`() = runTest {
        val id = "id"

        underTest.getCategoryById(id).isSuccess.shouldBeTrue()

        verify(dao).getById(id)
    }

    @Test
    fun `should get from dao when get category list`() = runTest {
        underTest.getCategoryList().count() shouldBeEqualTo 1

        verify(dao).getAll()
    }

    @Test
    fun `should insert with dao when save category list`() = runTest {
        underTest.saveCategoryList(listOf(SOME_CATEGORY)).isSuccess.shouldBeTrue()

        verify(dao).insertAll(anyVararg())
    }
}
