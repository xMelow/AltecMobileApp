package com.example.altecprint.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.altecprint.model.Label

@Composable
fun PrintLabelScreen(
    label: Label?,
    onPrintButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = label?.name ?: "no label selected"
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
    // foreach input field in input fields

    // input field for amount
    // button for printing
}