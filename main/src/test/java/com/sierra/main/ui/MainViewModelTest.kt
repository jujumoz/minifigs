package com.sierra.main.ui

import app.cash.turbine.test
import com.sierra.common.domain.GetMinifigsUseCase
import com.sierra.common.domain.UpdateMinifigsAndCategoriesUseCase
import com.sierra.common.domain.model.Minifig
import com.sierra.testutils.MainDispatcherExtension
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub

@ExtendWith(MainDispatcherExtension::class)
internal class MainViewModelTest {

    private val minifigId = "minifigId"
    private val minifig: Minifig = mock {
        on { id } doReturn minifigId
    }
    private val minifigs = listOf(minifig)

    private val getMinifigs: GetMinifigsUseCase = mock {
        onBlocking { invoke() } doReturn flowOf(minifigs)
    }
    private val updateMinifigsAndCategories: UpdateMinifigsAndCategoriesUseCase = mock {
        onBlocking { invoke() } doReturn Result.success(Unit)
    }

    private val underTest by lazy {
        MainViewModel(
            getMinifigs = getMinifigs,
            updateMinifigsAndCategories = updateMinifigsAndCategories,
        )
    }

    @Test
    fun `should do nothing when get update minifigs and categories is ok`() = runTest {
        underTest.uiEvent.test {
            expectNoEvents()
        }
    }

    @Test
    fun `should show error when get update minifigs and categories failed`() = runTest {
        val message = "it went wrong"
        updateMinifigsAndCategories.stub {
            onBlocking { invoke() } doReturn Result.failure(Exception(message))
        }

        underTest.uiEvent.test {
            awaitItem() shouldBeEqualTo MainViewModel.MainUiEvent.Error(message)
            expectNoEvents()
        }
    }

    @Test
    fun `should show minifigs list`() = runTest {
        underTest.uiModel.test {
            awaitItem() shouldBeEqualTo MainViewModel.MainUiModel.Loading
            awaitItem() shouldBeEqualTo MainViewModel.MainUiModel.Success(minifigs)
            expectNoEvents()
        }
    }

    @Test
    fun `should show retry when get minifigs list failed`() = runTest {
        val message = "it went wrong"
        getMinifigs.stub {
            onBlocking { invoke() } doReturn flow {
                throw Exception(message)
            }
        }

        underTest.uiModel.test {
            awaitItem() shouldBeEqualTo MainViewModel.MainUiModel.Loading
            awaitItem() shouldBeEqualTo MainViewModel.MainUiModel.Retry
            expectNoEvents()
        }
    }

    @Test
    fun `should show error when get minifigs list failed`() = runTest {
        val message = "it went wrong"
        getMinifigs.stub {
            onBlocking { invoke() } doReturn flow {
                throw Exception(message)
            }
        }

        underTest.uiEvent.test {
            awaitItem() shouldBeEqualTo MainViewModel.MainUiEvent.Error(message)
            expectNoEvents()
        }
    }

    @Test
    fun `should open detail when click on minifig`() = runTest {
        val underTest = underTest

        underTest.onMinifigClicked(minifig)

        underTest.uiEvent.test {
            awaitItem() shouldBeEqualTo MainViewModel.MainUiEvent.OpenDetail(minifigId)
            expectNoEvents()
        }
    }
}
