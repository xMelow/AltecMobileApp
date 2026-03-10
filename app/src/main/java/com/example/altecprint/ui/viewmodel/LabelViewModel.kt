package com.example.altecprint.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altecprint.core.PrinterManager
import com.example.altecprint.data.DataSource
import com.example.altecprint.data.LabelUiState
import com.example.altecprint.model.Label
import com.example.altecprint.util.TsplParser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LabelViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LabelUiState())
    val uiState: StateFlow<LabelUiState> = _uiState.asStateFlow()
    private val printManager = PrinterManager()

    init {
        _uiState.update { it.copy(labels = DataSource.labels) }
    }

    fun selectLabel(label: Label) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedLabel = label,
                variableData = TsplParser.parseVariableData(label.tspl),
                printerSettings = TsplParser.parsePrinterSettings(label.tspl)
            )
        }
    }

    fun addLabel(name: String, tspl: String) {
        _uiState.update { currentState ->
            val labelsWithAddedLabel = currentState.labels
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

    fun updateLabelVariableData(key: String, value: String) {
        _uiState.update { currentState ->
            currentState.copy(
                variableData = currentState.variableData + (key to value)
            )
        }
    }

    fun saveLabelTspl(tspl: String) {
        _uiState.update { currentState ->
            val updatedLabel = currentState.selectedLabel.updateTspl(tspl)
            updatedLabel.populateVariableDataMap()
            currentState.copy(
                selectedLabel = updatedLabel,
                variableData = updatedLabel.variableData
            )
        }
    }

    fun updateLabelPrintSettings(newPrinterSettings: Map<String, List<String>> ) {
        _uiState.update { currentState ->
            // update the label print settings
//            val currentLabel = currentState.selectedLabel
//            currentLabel.updatePrintSettings(newPrinterSettings)
            currentState.copy(
                printerSettings = newPrinterSettings
            )
        }
    }

    fun printLabel(label: Label?, labelAmount: String) {
        viewModelScope.launch {
            val finalTspl = label?.buildFinalTspl(_uiState.value.variableData)
            printManager.printLabel(finalTspl, labelAmount.toIntOrNull() ?: 1)
        }
    }


}