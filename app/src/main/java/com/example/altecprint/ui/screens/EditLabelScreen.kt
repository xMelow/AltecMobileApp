package com.example.altecprint.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.altecprint.model.Label

@Composable
fun EditLabelScreen(
    label: Label?,
    onSaveButtonClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var tspl by remember(label) { mutableStateOf(label?.tspl ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = tspl,
            onValueChange = { tspl = it },
            label = { Text(text = "TSPL") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f)
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { onSaveButtonClick(tspl ?: "") },
            ) {
                Text(text = "Save")
            }
        }
    }
}