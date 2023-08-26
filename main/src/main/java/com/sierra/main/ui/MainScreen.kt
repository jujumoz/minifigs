package com.sierra.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sierra.common.domain.model.Minifig
import com.sierra.common.ui.component.AppDivider
import com.sierra.common.ui.component.BasicScreen
import com.sierra.common.ui.component.strategy.ScrollStrategy
import com.sierra.common.ui.theme.AppTheme
import com.sierra.main.R
import com.sierra.main.ui.MainViewModel.MainUiModel
import kotlin.math.min

@Composable
fun MainScreen(
    uiModel: MainUiModel,
    onMinifigClicked: (Minifig) -> Unit,
    onRetryClicked: () -> Unit,
) {
    BasicScreen(
        title = stringResource(id = R.string.main_title),
        scrollStrategy = getScrollStrategy(),
    ) { scrollState ->
        when (uiModel) {
            MainUiModel.Loading -> Loading()
            MainUiModel.Retry -> Retry(onRetryClicked)
            is MainUiModel.Success -> MinifigList(
                scrollState = scrollState as LazyListState,
                minifigs = uiModel.minifigs,
                onMinifigClicked = onMinifigClicked,
            )
        }
    }
}

@Composable
fun getScrollStrategy(): ScrollStrategy {
    val lazyListState = rememberLazyListState()
    val offset = remember {
        derivedStateOf { lazyListState.firstVisibleItemScrollOffset / 600f + lazyListState.firstVisibleItemIndex }
    }
    val scrollOffset = min(1f, 1 - offset.value)

    return object : ScrollStrategy {
        override fun getScrollOffset(): Float = scrollOffset
        override fun getScrollState(): ScrollableState = lazyListState
    }
}

@Composable
fun Loading() {
    Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun Retry(onRetryClicked: () -> Unit) {
    Box(Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .clickable { onRetryClicked() },
            text = stringResource(id = R.string.retry),
        )
    }
}

@Composable
fun MinifigList(
    scrollState: LazyListState,
    minifigs: List<Minifig>,
    onMinifigClicked: (Minifig) -> Unit,
) {
    if (minifigs.isNotEmpty()) {
        val sortedMinifigs = minifigs.sortedBy { it.name }
        LazyColumn(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            state = scrollState,
        ) {
            items(
                sortedMinifigs,
                key = { minifig -> minifig.name }) { minifig ->
                AppDivider()
                CharacterRow(minifig, onMinifigClicked)
            }
        }
    } else {
        EmptyList()
    }
}

@Composable
private fun EmptyList() {
    Box(Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            style = MaterialTheme.typography.body2,
            text = stringResource(id = R.string.nothing_to_show),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    AppTheme {
        MainScreen(
            uiModel = someMainUiModel(),
            onMinifigClicked = {},
            onRetryClicked = {},
        )
    }
}

fun someMainUiModel(): MainUiModel {
    return MainUiModel.Success(
        listOf(
            Minifig(
                type = "inani",
                id = "vidisse",
                name = "Rachael Wagner",
                categoryId = "repudiandae",
                year = "sapien",
                weight = "quam"
            ),
            Minifig(
                type = "malesuada",
                id = "iusto",
                name = "Dorothea Gray",
                categoryId = "splendide",
                year = "graeco",
                weight = "enim"
            ),
        )
    )
}
