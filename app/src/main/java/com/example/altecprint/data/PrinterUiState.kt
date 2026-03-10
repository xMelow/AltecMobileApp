package com.example.altecprint.data

data class PrinterUiState (
    val printerPort: Int = 9100,
    val printerIpOrHostname: String = "192.168.1.123"
)