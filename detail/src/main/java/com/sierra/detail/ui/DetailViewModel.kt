package com.sierra.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sierra.common.domain.GetMinifigUseCase
import com.sierra.common.domain.model.Minifig
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class DetailViewModel @AssistedInject constructor(
    getMinifig: GetMinifigUseCase,
    @Assisted minifigId: String,
) : ViewModel() {

    private val _uiModel: MutableStateFlow<DetailUiModel> = MutableStateFlow(DetailUiModel.Loading)
    val uiModel: Flow<DetailUiModel> = _uiModel

    private val _uiEvent = MutableSharedFlow<DetailUiEvent>()
    val uiEvent: Flow<DetailUiEvent> = _uiEvent

    @AssistedFactory
    interface Factory {
        fun create(minifigId: String): DetailViewModel
    }

    init {
        viewModelScope.launch {
            getMinifig(minifigId)
                .onSuccess { minifig ->
                    _uiModel.emit(DetailUiModel.Success(minifig))
                }
                .onFailure { throwable ->
                    _uiEvent.emit(DetailUiEvent.Error(throwable.message.orEmpty()))
                }
        }
    }

    sealed class DetailUiModel {
        object Loading : DetailUiModel()
        data class Success(val minifig: Minifig) : DetailUiModel()
    }

    sealed class DetailUiEvent {
        data class Error(val message: String) : DetailUiEvent()
    }
}
