package com.example.altecprint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altecprint.core.PrinterManager
import com.example.altecprint.data.LabelUiState
import com.example.altecprint.model.Label
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LabelViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LabelUiState())
    val uiState: StateFlow<LabelUiState> = _uiState.asStateFlow()
    val printManager = PrinterManager()

    fun setLabel(label: Label) {
        _uiState.update { currentState ->
            currentState.copy(selectedLabel = label)
        }
    }

    fun printLabel(label: Label?) {
        viewModelScope.launch {
            printManager.printLabel(label)
        }
    }
}