package com.sierra.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sierra.common.domain.GetMinifigsUseCase
import com.sierra.common.domain.UpdateMinifigsAndCategoriesUseCase
import com.sierra.common.domain.model.Minifig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getMinifigs: GetMinifigsUseCase,
    private val updateMinifigsAndCategories: UpdateMinifigsAndCategoriesUseCase,
) : ViewModel() {

    private val _uiModel = MutableStateFlow<MainUiModel>(MainUiModel.Loading)
    val uiModel: Flow<MainUiModel> = _uiModel

    private val _uiEvent = MutableSharedFlow<MainUiEvent>()
    val uiEvent: Flow<MainUiEvent> = _uiEvent

    init {
        showMinifigs()
        updateData()
    }

    fun onRetryClicked() {
        updateData()
    }

    fun onMinifigClicked(minifig: Minifig) {
        viewModelScope.launch { _uiEvent.emit(MainUiEvent.OpenDetail(minifig.id)) }
    }

    private fun showMinifigs() {
        getMinifigs()
            .onStart { _uiModel.emit(MainUiModel.Loading) }
            .onEach { _uiModel.emit(MainUiModel.Success(it)) }
            .catch { throwable ->
                _uiModel.emit(MainUiModel.Retry)
                _uiEvent.emit(MainUiEvent.Error(throwable.message.orEmpty()))
            }.launchIn(viewModelScope)
    }

    private fun updateData() {
        viewModelScope.launch {
            updateMinifigsAndCategories().onFailure { throwable ->
                _uiEvent.emit(MainUiEvent.Error(throwable.message.orEmpty()))
            }
        }
    }

    sealed class MainUiModel {
        object Loading : MainUiModel()
        object Retry : MainUiModel()
        data class Success(val minifigs: List<Minifig>) : MainUiModel()
    }

    sealed class MainUiEvent {
        data class Error(val message: String) : MainUiEvent()
        data class OpenDetail(val id: String) : MainUiEvent()
    }
}
