package com.example.altecprint.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun PrinterScreen(
    printerIpOrHost: String,
    printerPort: Int,
    onConnectClick: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var ipOrHost by remember() { mutableStateOf("") }
    var port by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
    ) {
        OutlinedTextField(
            value = printerIpOrHost,
            onValueChange = { },
            label = { Text(text = "IP or Hostname") },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = printerPort.toString(),
            onValueChange = { port = it },
            label = { Text(text = "Port") },
            modifier = Modifier
                .fillMaxWidth()
        )

        Button(
            onClick = { onConnectClick(ipOrHost, port.toIntOrNull() ?: 9100) }
        ) {
            Text(text = "Connect")
        }
    }

}