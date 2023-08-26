package com.sierra.detail.ui

import app.cash.turbine.test
import com.sierra.common.domain.GetMinifigUseCase
import com.sierra.common.domain.model.Minifig
import com.sierra.testutils.MainDispatcherExtension
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub

@ExtendWith(MainDispatcherExtension::class)
internal class DetailViewModelTest {

    private val getMinifig: GetMinifigUseCase = mock()
    private val minifigId = "minifigId"
    private val minifig: Minifig = mock()

    private val underTest by lazy {
        DetailViewModel(
            getMinifig = getMinifig,
            minifigId = minifigId,
        )
    }

    @Test
    fun `should show success if get minifig is ok`() = runTest {
        getMinifig.stub {
            onBlocking { invoke(minifigId) } doReturn Result.success(minifig)
        }

        underTest.uiModel.test {
            awaitItem() shouldBeEqualTo DetailViewModel.DetailUiModel.Loading
            awaitItem() shouldBeEqualTo DetailViewModel.DetailUiModel.Success(minifig)
            expectNoEvents()
        }
        underTest.uiEvent.test {
            expectNoEvents()
        }
    }

    @Test
    fun `should show error if get minifig is ko`() = runTest {
        val error = "error"
        getMinifig.stub {
            onBlocking { invoke(minifigId) } doReturn Result.failure(IllegalStateException(error))
        }

        underTest.uiModel.test {
            awaitItem() shouldBeEqualTo DetailViewModel.DetailUiModel.Loading
            expectNoEvents()
        }
        underTest.uiEvent.test {
            awaitItem() shouldBeEqualTo DetailViewModel.DetailUiEvent.Error(error)
            expectNoEvents()
        }
    }
}
