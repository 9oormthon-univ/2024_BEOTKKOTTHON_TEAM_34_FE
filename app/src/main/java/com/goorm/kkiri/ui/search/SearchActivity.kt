package com.goorm.kkiri.ui.search

import android.content.Context
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivitySearchBinding
import com.goorm.kkiri.ui.search.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {
    private var input = ""
    private val viewModel by viewModels<SearchViewModel>()

    override fun setLayout() {
        search()
        binding.ivSearchBackIcon.setOnClickListener {
            finish()
        }
        observe()
    }

    private fun search() {
        binding.etInputSearch.doAfterTextChanged {
            if (!it.isNullOrBlank()) {
                input = it.toString()
            }
        }
        binding.etInputSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.searchResult(input, input, 0)
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.etInputSearch.windowToken, 0)
                true
            } else {
                false
            }
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.searchResult.collectLatest {
                    if (it.status == "OK") {

                    }
                }
            }
        }
    }
}