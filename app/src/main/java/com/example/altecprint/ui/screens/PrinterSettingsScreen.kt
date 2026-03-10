package com.example.altecprint.ui.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import kotlin.collections.component1
import kotlin.collections.component2

@Composable
fun PrinterSettingsScreen(
    printerSettings: Map<String, List<String>>,
    onSaveButtonClicked: (Map<String, List<String>>) -> Unit,
    modifier: Modifier = Modifier
) {
    //TODO: Remove mm from input fields
    //TODO: On off change to dropdown

    var newPrinterSettings by remember { mutableStateOf(printerSettings) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        newPrinterSettings.forEach { (key, value) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = key,
                    modifier = Modifier.weight(1f)
                )

                value.forEachIndexed { index, param ->
                    OutlinedTextField(
                        value = param,
                        onValueChange = {
                            val updatedParamList = value.toMutableList().also { list -> list[index] = it }
                            newPrinterSettings = newPrinterSettings + (key to updatedParamList)
                        },
                        label = { },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { onSaveButtonClicked(newPrinterSettings) }) {
                Text(text = "Save")
            }
        }
    }
}