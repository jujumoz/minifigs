package com.sierra.common.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CollapsingToolbar(
    image: Painter,
    title: String,
    scrollOffset: Float,
    onBackPressed: (() -> Unit)? = null,
) {
    val threshold = 0.5f
    val imageSize by animateDpAsState(targetValue = if (scrollOffset > threshold) 128.dp else 62.dp)
    val subtitleSize by animateDpAsState(targetValue = if (scrollOffset > threshold) Dp.Unspecified else 0.dp)
    val paddingScale by animateDpAsState(targetValue = if (scrollOffset > threshold) 16.dp else 0.dp)
    Box(
        Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background),
    ) {
        onBackPressed?.let { callback ->
            IconButton(
                modifier = Modifier.height(62.dp),
                onClick = { callback() },
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colors.secondary,
                )
            }
        }
        Column(
            Modifier
                .fillMaxWidth(),
        ) {
            Image(
                modifier = Modifier
                    .height(imageSize)
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                painter = image,
                contentDescription = "logo",
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(subtitleSize)
                    .padding(paddingScale),
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
                text = title,
            )
        }
    }
}
