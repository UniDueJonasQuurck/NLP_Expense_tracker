package com.example.nlp_expense_tracker.fragments.Graph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.example.nlp_expense_tracker.Database.ReceiptDao
import com.example.nlp_expense_tracker.Database.Receipts
import com.example.nlp_expense_tracker.R
import com.example.nlp_expense_tracker.fragments.History.HistoryFragment
import com.example.nlp_expense_tracker.fragments.History.PurchaseViewmodel
import com.robinhood.spark.SparkAdapter
import com.robinhood.spark.SparkView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList


@AndroidEntryPoint
class ChartFragment : Fragment(R.layout.fragment_chart) {


    private val viewModel: PurchaseViewmodel by viewModels()
    private lateinit var sparkView: SparkView
    private lateinit var backToHistory: Button
    private lateinit var receiptsSparkAdapter: SparkAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?):
            View {val view: View = inflater.inflate(R.layout.fragment_chart, container, false)
        backToHistory = view.findViewById(R.id.backToHistory)
        sparkView = view.findViewById(R.id.sparkView)


        backToHistory.setOnClickListener{
            replaceFragment(HistoryFragment())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentChart, fragment)
        transaction.commitNowAllowingStateLoss()
    }
}
