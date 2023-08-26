package com.sierra.common.repository

import com.sierra.common.domain.model.SOME_CATEGORY
import com.sierra.common.local.datasource.LocalCategoryDataSource
import com.sierra.common.network.datasource.RemoteCategoryDataSource
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class CategoryRepositoryImplTest {

    private val categories = listOf(SOME_CATEGORY)

    private val remoteCategoryDataSource: RemoteCategoryDataSource = mock {
        onBlocking { getCategoryList() } doReturn Result.success(categories)
    }
    private val localCategoryDataSource: LocalCategoryDataSource = mock()

    private val underTest = CategoryRepositoryImpl(
        remoteCategoryDataSource = remoteCategoryDataSource,
        localCategoryDataSource = localCategoryDataSource,
    )

    @Test
    fun `should save to local source when get categories from remote source`() = runTest {
        underTest.updateCategoryList()

        verify(remoteCategoryDataSource).getCategoryList()
        verify(localCategoryDataSource).saveCategoryList(categories)
    }

    @Test
    fun `should get from local data source when get category list`() = runTest {
        underTest.getCategoryList()

        verify(localCategoryDataSource).getCategoryList()
    }

    @Test
    fun `should get from local data source when get category`() = runTest {
        val id = "id"

        underTest.getCategoryById(id)

        verify(localCategoryDataSource).getCategoryById(id)
    }
}
