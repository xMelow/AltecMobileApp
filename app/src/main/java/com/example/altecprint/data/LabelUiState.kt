package com.example.altecprint.data

import com.example.altecprint.model.Label

data class LabelUiState (
    val labels: List<Label> = listOf(
        Label("Testlabel 1", "CLS"),
        Label("Keurings Label", "this is a test")
    )
)