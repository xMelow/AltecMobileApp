package com.example.altecprint.data

import com.example.altecprint.model.Label

data class LabelUiState (
    val labels: MutableList<Label> = DataSource.labels,
    val selectedLabel: Label,
    val labelAmount: String = "1",
    val variableData: Map<String, String>,
    val printerSettings: Map<String, List<String>>,
)