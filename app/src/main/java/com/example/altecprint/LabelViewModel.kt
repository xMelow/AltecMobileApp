package com.example.altecprint

import androidx.lifecycle.ViewModel
import com.example.altecprint.data.LabelUiState
import com.example.altecprint.model.Label
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LabelViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LabelUiState())
    val uiState: StateFlow<LabelUiState> = _uiState.asStateFlow()

    fun setLabel(label: Label) {
        _uiState.update { currentState ->
            currentState.copy(selectedLabel = label)
        }
    }
}