package com.sierra.main.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sierra.common.ui.R
import com.sierra.common.ui.theme.setThemedContent
import com.sierra.common.ui.utils.launchAndCollectWhileStarted
import com.sierra.common.ui.utils.showError
import com.sierra.main.ui.MainViewModel.MainUiEvent
import com.sierra.main.ui.MainViewModel.MainUiModel
import com.sierra.navigation.DetailNavigation
import dagger.android.AndroidInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var detailNavigation: DetailNavigation

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setupUI()
        collectFlows()
    }

    private fun setupUI() {
        lifecycleScope.launch {
            setThemedContent {
                val state = viewModel.uiModel.collectAsState(initial = MainUiModel.Loading)
                MainScreen(
                    uiModel = state.value,
                    onRetryClicked = viewModel::onRetryClicked,
                    onMinifigClicked = viewModel::onMinifigClicked,
                )
            }
        }
    }

    private fun collectFlows() {
        launchAndCollectWhileStarted(viewModel.uiEvent) {
            when (it) {
                is MainUiEvent.Error ->
                    showError(getString(R.string.error_placeholder).format(it.message))

                is MainUiEvent.OpenDetail -> openDetail(it.id)
            }
        }
    }

    private fun openDetail(minifigId: String) {
        startActivity(
            detailNavigation.createIntent(this@MainActivity, minifigId)
        )
    }
}
