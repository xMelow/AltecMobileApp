package com.example.altecprint

import androidx.lifecycle.ViewModel
import com.example.altecprint.data.LabelUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LabelViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LabelUiState())
    val uiState: StateFlow<LabelUiState> = _uiState.asStateFlow()
}