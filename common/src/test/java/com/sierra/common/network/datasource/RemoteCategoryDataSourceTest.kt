package com.sierra.common.network.datasource

import com.sierra.common.domain.model.SOME_REMOTE_CATEGORY
import com.sierra.common.network.service.ApiService
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeTrue
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class RemoteCategoryDataSourceTest {

    private val service: ApiService = mock {
        onBlocking { getCategories() } doReturn listOf(SOME_REMOTE_CATEGORY)
    }

    private val underTest = RemoteCategoryDataSource(service)

    @Test
    fun `should get from api service when get category list`() = runTest {
        underTest.getCategoryList().isSuccess.shouldBeTrue()

        verify(service).getCategories()
    }
}
