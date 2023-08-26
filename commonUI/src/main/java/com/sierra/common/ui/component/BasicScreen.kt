package com.sierra.common.ui.component

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.sierra.common.ui.R
import com.sierra.common.ui.component.strategy.ScrollStrategy

@Composable
fun BasicScreen(
    title: String,
    scrollStrategy: ScrollStrategy,
    onBackPressed: (() -> Unit)? = null,
    content: @Composable ColumnScope.(scrollState: ScrollableState) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CollapsingToolbar(
                image = painterResource(id = R.drawable.lego_logo),
                title = title,
                scrollOffset = scrollStrategy.getScrollOffset(),
                onBackPressed = onBackPressed,
            )
            content(scrollStrategy.getScrollState())
        }
    }
}
