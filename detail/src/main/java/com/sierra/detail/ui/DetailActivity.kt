package com.sierra.detail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import com.sierra.common.ui.R
import com.sierra.common.ui.theme.setThemedContent
import com.sierra.common.ui.utils.getSerializableExtraCompat
import com.sierra.common.ui.utils.launchAndCollectWhileStarted
import com.sierra.common.ui.utils.showError
import com.sierra.detail.ui.DetailViewModel.DetailUiEvent
import com.sierra.detail.ui.DetailViewModel.DetailUiModel
import dagger.android.AndroidInjection
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    private val minifigId by lazy { intent.getSerializableExtraCompat<String>(EXTRA_MINIFIG_ID)!! }

    @Inject
    lateinit var viewModelFactory: DetailViewModel.Factory

    private val viewModel: DetailViewModel by lazy { viewModelFactory.create(minifigId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setupUI()
        collectFlows()
    }

    private fun setupUI() {
        lifecycleScope.launch {
            setThemedContent {
                val state = viewModel.uiModel.collectAsState(initial = DetailUiModel.Loading)
                DetailScreen(state.value) {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }
    }

    private fun collectFlows() {
        launchAndCollectWhileStarted(viewModel.uiEvent) {
            when (it) {
                is DetailUiEvent.Error ->
                    showError(getString(R.string.error_placeholder).format(it.message))
            }
        }
    }

    companion object {
        fun createIntent(context: Context, minifigId: String): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_MINIFIG_ID, minifigId)
            }
        }

        private const val EXTRA_MINIFIG_ID = "EXTRA_MINIFIG_ID"
    }
}
