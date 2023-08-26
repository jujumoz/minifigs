package com.sierra.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sierra.common.domain.model.Minifig
import com.sierra.common.ui.theme.AppTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterRow(
    minifig: Minifig,
    onMinifigClicked: (Minifig) -> Unit,
) {
    Row(
        Modifier
            .height(100.dp)
            .clickable { }
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .clickable { onMinifigClicked(minifig) }
    ) {
        GlideImage(
            model = minifig.image(),
            modifier = Modifier.aspectRatio(1f),
            contentDescription = "character",
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopCenter,
            alpha = 0.9f,
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.End,
            text = minifig.shortName(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterRowPreview() {
    AppTheme {
        CharacterRow(someMinifig()) {}
    }
}

private fun someMinifig() = Minifig(
    type = "venenatis",
    id = "brute",
    name = "Maritza Finch",
    categoryId = "quaestio",
    year = "qui",
    weight = "sit",
)
