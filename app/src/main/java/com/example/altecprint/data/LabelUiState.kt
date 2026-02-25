package com.example.altecprint.data

import com.example.altecprint.model.Label

data class LabelUiState (
    val selectedLabel: Label? = null,
    val labelAmount: String = "1",
    val variableData: Map<String, String> = emptyMap(),

    val printerPort: Int = 9100,
    val printerIpOrHostname: String = "192.168.1.123"
)