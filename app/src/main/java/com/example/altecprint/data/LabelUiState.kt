package com.example.altecprint.data

import com.example.altecprint.model.Label
import java.util.Collections.emptyList

data class LabelUiState (
    val labels: MutableList<Label> = emptyList(),
    val selectedLabel: Label = DataSource.labels[0],
    val labelAmount: String = "1",
    val variableData: Map<String, String> = emptyMap(),
    val printerSettings: Map<String, List<String>> = emptyMap(),
)