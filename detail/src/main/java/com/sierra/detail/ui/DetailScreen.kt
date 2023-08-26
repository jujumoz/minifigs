package com.sierra.detail.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sierra.common.domain.model.Minifig
import com.sierra.common.ui.component.AppDivider
import com.sierra.common.ui.component.BasicScreen
import com.sierra.common.ui.component.strategy.ScrollStrategy
import com.sierra.common.ui.theme.AppTheme
import com.sierra.detail.R
import com.sierra.detail.ui.DetailViewModel.DetailUiModel
import com.sierra.detail.ui.component.Line
import com.sierra.detail.ui.component.Title
import kotlin.math.min

@Composable
fun DetailScreen(
    detailUiModel: DetailUiModel,
    onBackPressed: () -> Unit,
) {
    when (detailUiModel) {
        DetailUiModel.Loading -> Loading()
        is DetailUiModel.Success -> MinifigDetails(
            minifig = detailUiModel.minifig,
            onBackPressed = onBackPressed,
        )
    }
}

@Composable
private fun Loading() {
    Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
private fun MinifigDetails(
    minifig: Minifig,
    onBackPressed: () -> Unit,
) {
    BasicScreen(
        title = minifig.shortName(),
        scrollStrategy = getScrollStrategy(),
        onBackPressed = onBackPressed,
    ) { scrollState ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.background)
                .verticalScroll(scrollState as ScrollState),
        ) {
            AppDivider()
            GlideImage(
                model = minifig.image(),
                contentDescription = minifig.name,
                contentScale = ContentScale.FillWidth,
            )
            IdentitySection(minifig)
            MoreDataSection(minifig)
        }
    }
}

@Composable
private fun IdentitySection(minifig: Minifig) {
    Title(stringResource(id = R.string.identity))
    Line(stringResource(id = R.string.full_name), minifig.fullName())
    minifig.details()?.let { Line(stringResource(id = R.string.details), it) }
}

@Composable
private fun MoreDataSection(minifig: Minifig) {
    Title(stringResource(id = R.string.more_data))
    Line(stringResource(id = R.string.year), minifig.year)
    Line(stringResource(id = R.string.category), minifig.categoryName)
    Line(stringResource(id = R.string.weight), "${minifig.weight}g")
}

@Composable
fun getScrollStrategy(): ScrollStrategy {
    val scrollState = rememberScrollState()
    val scrollOffset = min(
        1f,
        1 - scrollState.value.toFloat().div(scrollState.maxValue),
    )

    return object : ScrollStrategy {
        override fun getScrollOffset(): Float = scrollOffset
        override fun getScrollState(): ScrollableState = scrollState
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    AppTheme {
        DetailScreen(
            detailUiModel = DetailUiModel.Success(
                Minifig(
                    type = "periculis",
                    id = "menandri",
                    name = "Ava Buckley",
                    categoryId = "appareat",
                    year = "splendide",
                    weight = "fabellas"
                )
            )
        ) {}
    }
}
