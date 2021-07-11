package com.example.nlp_expense_tracker.fragments.History

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.nlp_expense_tracker.Database.ReceiptDao
import com.example.nlp_expense_tracker.Database.ReceiptDatabase
import com.example.nlp_expense_tracker.Database.Receipts
import com.example.nlp_expense_tracker.R
import com.example.nlp_expense_tracker.databinding.FragmentHistoryBinding
import com.example.nlp_expense_tracker.fragments.Graph.ChartFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history) {

    private val viewModel: PurchaseViewmodel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHistoryBinding.bind(view)

        val exampleAdapter = ExampleAdapter()

        binding.apply{
            recyclerView.apply{

                layoutManager = LinearLayoutManager(requireContext())
                adapter = exampleAdapter

            }
            viewModel.totalSum.observe(viewLifecycleOwner) { totalSumTextView.text = it.toString()}

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false /// We dont need any interaction with Drag&Drop we only want swipe left&right
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val receipt = exampleAdapter.currentList[viewHolder.adapterPosition]
                     viewModel.onSwipe(receipt)
                }
            }).attachToRecyclerView(recyclerView)

            buttonChart.apply {
                setOnClickListener {
                   replaceFragment(ChartFragment())
                }
            }

        }


        setFragmentResultListener("add_receipt_request"){_,bundle ->
            val result = bundle.getInt("add_receipt_request")
            viewModel.onAddResult(result)
        }

        viewModel.receipts.observe(viewLifecycleOwner){ /// New Items get passed to the List
            exampleAdapter.submitList(it)
        }


        viewLifecycleOwner.lifecycleScope.launchWhenStarted { //as soon as we close our app the events will be suspended, but not deleted and will remain after restart
            viewModel.addTaskEvent.collect {  event->
                when(event){
                    is PurchaseViewmodel.TasksEvent.ShowUndoDelete -> {
                        Snackbar.make(requireView(),"Tasks deleted", Snackbar.LENGTH_LONG)
                            .setAction("UNDO"){
                                viewModel.unDoDeleteClick(event.receipts)
                            }.show()
                    }
                }
            }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentChart, fragment)
        transaction.commitNowAllowingStateLoss()
    }
}