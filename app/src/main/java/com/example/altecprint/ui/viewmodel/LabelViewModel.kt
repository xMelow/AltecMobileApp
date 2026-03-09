package com.example.altecprint.ui.viewmodel

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
    private val printManager = PrinterManager()

    fun setLabel(label: Label) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedLabel = label,
                variableData = label.variableData.toMap(),
                printerSettings = label.printerSettings.toMap()
            )
        }
    }

    fun addLabel(name: String, tspl: String) {
        _uiState.update { currentState ->
            var labelsWithAddedLabel = currentState.labels
            labelsWithAddedLabel.add(Label(name, tspl))
            currentState.copy(
                labels = labelsWithAddedLabel
            )
        }
    }

    fun updateLabelAmount(labelAmount: String) {
        _uiState.update { currentState ->
            currentState.copy(labelAmount = labelAmount)
        }
    }

    fun updateLabelVariable(key: String, value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                variableData = currentState.variableData + (key to value)
            )
        }
    }

    fun saveLabelTspl(tspl: String) {
        _uiState.update { currentState ->
            val updatedLabel = currentState.selectedLabel?.updateTspl(tspl)
            updatedLabel?.populateVariableDataMap()
            currentState.copy(
                selectedLabel = updatedLabel,
                variableData = updatedLabel?.variableData ?: emptyMap()
            )
        }
    }

    fun updateLabelPrintSettings(newPrinterSettings: Map<String, List<String>> ) {
        _uiState.update { currentState ->
            currentState.copy(
                printerSettings = newPrinterSettings
            )
        }
    }

    fun updateLabelPrintSetting(key: String, param: String) {
        _uiState.update { currentState ->
            // find key value par update map
            currentState.copy(
                printerSettings = (currentState.printerSettings + (key to param)) as Map<String, List<String>>
            )
        }
    }

    fun printLabel(label: Label?, labelAmount: String) {
        viewModelScope.launch {
            val finalTspl = label?.buildFinalTspl(_uiState.value.variableData)
            printManager.printLabel(finalTspl, labelAmount.toIntOrNull() ?: 1)
        }
    }

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