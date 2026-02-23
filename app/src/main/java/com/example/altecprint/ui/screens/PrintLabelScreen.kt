package com.example.altecprint.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
    label: Label?,
    amount: String,
    onLabelAmountChange: (String) -> Unit,
    onPrintButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    //TODO: label preview
    //TODO: text fields for variable data

    Column(
        modifier = modifier
    ) {
        Text(
            text = label?.name ?: "no label selected"
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextField(
                value = amount,
                onValueChange = { onLabelAmountChange(it) },
                label = { Text(text = "Label Amount: ") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
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
        label = Label("test", "test"),
        amount = "1",
        onLabelAmountChange = {},
        onPrintButtonClicked = {}
    )
}