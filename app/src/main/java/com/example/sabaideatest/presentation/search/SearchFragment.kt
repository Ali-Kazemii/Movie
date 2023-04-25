package com.example.sabaideatest.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sabaideatest.databinding.ContainSearchBinding
import com.example.sabaideatest.databinding.FragmentSearchBinding
import com.example.sabaideatest.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private var binding by autoCleared<FragmentSearchBinding>()
    private var containBinding by autoCleared<ContainSearchBinding>()
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        containBinding = binding.contain
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        handleEvent()
    }

    private fun initView() {
        searchAdapter = SearchAdapter()
        containBinding.recyclerViewSearch.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = searchAdapter
        }
    }

    private fun initObserver() {
        viewModel.progress.observe(viewLifecycleOwner) {
            binding.progress.isVisible = it
        }
        viewModel.failure.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.listMovie.observe(viewLifecycleOwner) { response ->
            response.let { list ->
                if (list.isNotEmpty()) {
                    containBinding.layoutSearchHint.isVisible = false
                    searchAdapter.submitList(list.toMutableList())
                } else
                    containBinding.layoutSearchHint.isVisible = true
            }
        }
    }

    private fun handleEvent() {
        binding.editTextSearch.doAfterTextChanged {
            if (it != null && it.trim().length >= 2) {
                searchAdapter.submitList(null)
                viewModel.getMovie(it.toString())
            } else if (it.isNullOrEmpty())
                clear()
        }
        binding.buttonClear.setOnClickListener {
            binding.editTextSearch.setText("")
        }
        binding.buttonBack.setOnClickListener {
            activity?.finish()
        }
    }

    private fun clear() {
        searchAdapter.submitList(null)
        containBinding.layoutSearchHint.isVisible = true
        binding.editTextSearch.requestFocus()
    }
}