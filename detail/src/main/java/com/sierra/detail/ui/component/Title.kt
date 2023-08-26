package com.sierra.detail.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sierra.common.ui.component.AppDivider
import com.sierra.common.ui.theme.AppTheme

@Composable
fun Title(text: String) {
    AppDivider()
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Start,
        text = text,
    )
    AppDivider()
}

@Preview(showBackground = true)
@Composable
fun TitlePreview() {
    AppTheme {
        Title(text = "ex")
    }
}
