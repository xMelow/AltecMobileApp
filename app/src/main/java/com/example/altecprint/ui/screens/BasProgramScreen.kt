package com.example.altecprint.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BasProgramScreen(
    onSendButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var inputValue by remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            label = { Text(text = "Input: ") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            modifier = Modifier.weight(1f),
            onClick = { onSendButtonClicked(inputValue) }
        ) {
            Text(text = "Send")
        }
    }
}

@Preview
@Composable
fun BasProgramScreenPreview() {
    BasProgramScreen(
        onSendButtonClicked = {}
    )
}