package com.sierra.detail.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sierra.common.ui.theme.AppTheme

@Composable
fun Line(
    text: String,
    value: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Start,
            text = text,
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.End,
            text = value,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LinePreview() {
    AppTheme {
        Line(text = "delenit", value = "quis")
    }
}
