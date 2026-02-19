package com.example.altecprint.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.altecprint.model.Label

@Composable
fun LabelScreen(
    labels: List<Label>,
    onLabelClick: (Label) -> Unit,
    modifier: Modifier = Modifier
    ) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(labels) { label ->
            LabelCard(
                label = label,
                onLabelClick = onLabelClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun LabelCard(
    label: Label,
    onLabelClick: (Label) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onLabelClick(label) },
        modifier = modifier
    ) {
        Text(
            text = label.name,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
private fun LabelCardPreview() {
    LabelCard(
        onLabelClick = {},
        label = Label("test", "exmaple tspl")
    )
}