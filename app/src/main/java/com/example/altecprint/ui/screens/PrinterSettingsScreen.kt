package com.example.altecprint.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
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
    //TODO: Remove mm from input fields
    //TODO: On off change to dropdown
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        printerSettings.forEach { (key, value) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = key,
                    modifier = Modifier.weight(1f)
                )

                value.forEach { param ->
                    OutlinedTextField(
                        value = param,
                        onValueChange = { onSettingChanged() },
                        label = { Text(text = key) },
                        modifier = Modifier.fillMaxWidth(0.5f)
                            .padding(4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier,
        ) {
            Button(onClick = onConnectButtonClicked) {
                Text(text = "Connect to printer")
            }

            Button(onClick = onSaveButtonClicked) {
                Text(text = "Save")
            }
        }
    }
}