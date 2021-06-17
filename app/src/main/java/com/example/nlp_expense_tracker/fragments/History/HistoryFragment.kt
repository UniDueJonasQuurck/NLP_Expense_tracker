package com.example.nlp_expense_tracker.fragments.History

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.View
import androidx.fragment.app.setFragmentResultListener

import androidx.fragment.app.viewModels

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nlp_expense_tracker.R

import com.example.nlp_expense_tracker.databinding.FragmentHistoryBinding
import com.example.nlp_expense_tracker.fragments.PurchaseViewmodel

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history) {

    private val viewModel: PurchaseViewmodel by viewModels()




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHistoryBinding.bind(view)

        val exampleAdapter = ExampleAdapter()

        binding.apply{
            recyclerView.apply{

                layoutManager = LinearLayoutManager(requireContext())
                adapter = exampleAdapter
                setHasFixedSize(true)
            }
        }

        setFragmentResultListener("add_receipt_request"){_,bundle ->
            val result = bundle.getInt("add_receipt_request")
            viewModel.onAddResult(result)
        }

        viewModel.receipts.observe(viewLifecycleOwner){ /// New Items get passed to the List
            exampleAdapter.submitList(it)
        }
    }
}