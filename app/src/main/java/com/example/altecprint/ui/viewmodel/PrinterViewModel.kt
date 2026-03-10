package com.example.altecprint.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altecprint.core.PrinterManager
import com.example.altecprint.data.LabelUiState
import com.example.altecprint.data.PrinterUiState
import com.example.altecprint.model.Label
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PrinterViewModel: ViewModel() {

    private val printManager = PrinterManager()
    private val _uiState = MutableStateFlow((PrinterUiState()))
    val uiState: StateFlow<PrinterUiState> = _uiState.asStateFlow()

    fun sendBasData(data: String) {
        viewModelScope.launch {
            printManager.sendBasData(data)
        }
    }

    fun exitBasProgram() {
        viewModelScope.launch {
            printManager.sendBasData("EXIT")
        }
    }

    fun connectToPrinter(ipOrHostname: String, port: Int) {
        viewModelScope.launch {
            printManager.connectToPrinter(ipOrHostname, port)
        }
    }
}