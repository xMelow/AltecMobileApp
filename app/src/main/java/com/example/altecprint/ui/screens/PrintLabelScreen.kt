package com.example.altecprint.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.altecprint.model.Label

@Composable
fun PrintLabelScreen(
    amount: String,
    variableData: Map<String, String>,
    onLabelAmountChange: (String) -> Unit,
    onVariableChange: (String, String) -> Unit,
    onEditButtonClicked: () -> Unit,
    onPrintButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    //TODO: label preview?

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        variableData.forEach { (key, value) ->
            OutlinedTextField(
                value = value,
                onValueChange = { onVariableChange(key, it) },
                label = { Text(text = key) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = onEditButtonClicked
            ) {
                Text(text = "Edit TSPL")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextField(
                value = amount,
                onValueChange = { onLabelAmountChange(it) },
                label = { Text(text = "Label Amount: ") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            Button(
                modifier = Modifier.weight(1f),
                onClick = onPrintButtonClicked
            ) {
                Text(
                    text = "Print"
                )
            }
        }
    }
}

@Preview
@Composable
fun PrintLabelPreview() {
    PrintLabelScreen(
        amount = "1",
        variableData = mapOf(),
        onLabelAmountChange = {},
        onPrintButtonClicked = {},
        onEditButtonClicked = {},
        onVariableChange = {} as (String, String) -> Unit
    )
}