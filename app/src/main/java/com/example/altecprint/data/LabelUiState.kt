package com.example.altecprint.data

import com.example.altecprint.model.Label

data class LabelUiState (
    val selectedLabel: Label? = null,
    val labelAmount: String = "1",
    val variableData: Map<String, String> = emptyMap()
)