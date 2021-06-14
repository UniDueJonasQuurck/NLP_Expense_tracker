package com.example.nlp_expense_tracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nlp_expense_tracker.fragments.History.ExampleAdapter
import com.example.nlp_expense_tracker.fragments.History.ExamplePurchase
import com.example.nlp_expense_tracker.R
import com.example.nlp_expense_tracker.fragments.History.HeaderAdapter
import okhttp3.internal.http2.Header

class HistoryFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?):
            View {val view: View = inflater.inflate(R.layout.fragment_history, container, false)

        val exampleList = generateDummyList(500)
        val recycler_view: RecyclerView = view.findViewById(R.id.recycler_view)
        val recylerAdapter = ExampleAdapter(exampleList)
        val header_view  = HeaderAdapter()
        val concatAdapter = ConcatAdapter(header_view, recylerAdapter)
            recycler_view.adapter = concatAdapter
        recycler_view.layoutManager = LinearLayoutManager(view.context)
        recycler_view.setHasFixedSize(true)

        return view
    }

    private fun generateDummyList(size: Int): List<ExamplePurchase> {
        val list = ArrayList<ExamplePurchase>()
        for (i in 0 until size) {
            val item = ExamplePurchase("13.06.21", "Aldi", "24,76€")
            list += item
        }
        return list
    }


}