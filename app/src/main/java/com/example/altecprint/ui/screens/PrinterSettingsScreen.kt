package com.example.altecprint.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.collections.component1
import kotlin.collections.component2

@Composable
fun PrinterSettingsScreen(
    printerSettings: Map<String, String>,
    onSettingChanged: (String, String) -> Unit,
    onConnectButtonClicked: () -> Unit,
    onSaveButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            printerSettings.forEach { (key, value) ->
                OutlinedTextField(
                    value = value,
                    onValueChange = { onSettingChanged(key, it) },
                    label = { Text(text = key) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier)

        Row(

        ) {
            Button(
                onClick = {}
            ) {
                Text(text = "Connect to printer")
            }

            Button(
                onClick = {}
            ) {
                Text(text = "Save")
            }
        }
    }
}