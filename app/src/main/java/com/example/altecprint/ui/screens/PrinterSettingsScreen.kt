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
    printerSettings: Map<String, List<String>>,
    onSettingChanged: () -> Unit,
    onConnectButtonClicked: () -> Unit,
    onSaveButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        printerSettings.forEach { (key, value) ->
            Row(
                modifier = Modifier
            ) {
                Text(
                    text = key,
                    modifier = Modifier.fillMaxWidth()
                )

                value.forEach { param ->
                    OutlinedTextField(
                        value = param,
                        onValueChange = { },
                        label = { Text(text = param) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Spacer(modifier = Modifier)

        Row(
            modifier = Modifier,
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