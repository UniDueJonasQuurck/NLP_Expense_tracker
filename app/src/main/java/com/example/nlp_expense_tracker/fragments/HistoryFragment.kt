package com.example.nlp_expense_tracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment

import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.fragment.app.viewModels

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nlp_expense_tracker.R

import com.example.nlp_expense_tracker.fragments.History.ExampleAdapter

import com.example.nlp_expense_tracker.databinding.FragmentHistoryBinding
import dagger.Provides

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
        viewModel.receipts.observe(viewLifecycleOwner){ /// New Items get passed to the List
            exampleAdapter.submitList(it)
        }
    }
}