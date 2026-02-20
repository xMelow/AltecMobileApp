package com.example.altecprint.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.altecprint.model.Label

@Composable
fun PrintLabelScreen(
    label: Label?,
    modifier: Modifier = Modifier
) {
    Text(
        text = label?.name ?: "no label selected"
    )

    // foreach input field in input fields

    // input field for amount
    // button for printing
}