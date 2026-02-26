package com.example.altecprint.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PrinterConnectScreen(
    printerIpOrHost: String,
    printerPort: Int,
    onConnectClick: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var ipOrHost by remember { mutableStateOf(printerIpOrHost) }
    var port by remember { mutableStateOf(printerPort.toString()) }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = ipOrHost,
            onValueChange = { ipOrHost = it },
            label = { Text(text = "IP or Hostname") },
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = port,
            onValueChange = { port = it },
            label = { Text(text = "Port") },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Button(
                onClick = { onConnectClick(ipOrHost, port.toIntOrNull() ?: 9100) }
            ) {
                Text(text = "Connect")
            }
        }
    }

}