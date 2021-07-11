package com.example.nlp_expense_tracker.fragments.Home


import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.viewpager.widget.ViewPager
import com.example.nlp_expense_tracker.R

import com.example.nlp_expense_tracker.databinding.FragmentHomeBinding
import com.example.nlp_expense_tracker.fragments.History.PurchaseViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.max


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var monthlyExpense: TextView
    private lateinit var budgetProgressBar: ProgressBar
    private lateinit var viewPager: ViewPager
    private val purchaseViewmodel: PurchaseViewmodel by viewModels()
    var monthlyBudget : Int  = 250
    private lateinit var seekBar: SeekBar
    private lateinit var textView4: TextView
    private lateinit var monthlyPercent: TextView
    private lateinit var monthlyBudgetText: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        monthlyExpense = view.findViewById(R.id.monthlyExpense)
        viewPager = requireActivity().findViewById(R.id.viewPager)
        budgetProgressBar = view.findViewById(R.id.budgetProgressBar)
        monthlyBudgetText = view.findViewById(R.id.monthlyBudgetText)
        monthlyPercent = view.findViewById(R.id.monthlyPercent)


        val binding = FragmentHomeBinding.bind(view)

        binding.apply {
            recentPurchase1.apply {
                setOnClickListener {
                    viewPager.setCurrentItem(2)
                }
            }
            recentPurchase2.apply {
                setOnClickListener {
                    viewPager.setCurrentItem(2)
                }
            }
            recentPurchase3.apply {
                setOnClickListener {
                    viewPager.setCurrentItem(2)
                }
            }
            recentPurchase4.apply {
                setOnClickListener {
                    viewPager.setCurrentItem(2)
                }
            }
            viewModel.latestEntryStore.observe(viewLifecycleOwner) {
                if (it != null ) {// Fetches the function from the Viewmodel to display data
                    recentPurchase1Store.text = it.toString()
                }else
                    recentPurchase1Store.text = ""
            }
            viewModel.latestEntryTotal.observe(viewLifecycleOwner) {
                recentPurchase1Total.text = it.toString()
                if (it != null ) {// Fetches the function from the Viewmodel to display data
                    recentPurchase1Total.text = it.toString()
                }else
                    recentPurchase1Total.text = ""
            }
            viewModel.secondLatestEntryStore.observe(viewLifecycleOwner) {
                if (it != null ) {// Fetches the function from the Viewmodel to display data
                    recentPurchase2Store.text = it.toString()
                }else
                    recentPurchase2Store.text = ""
            }
            viewModel.secondLatestEntryTotal.observe(viewLifecycleOwner) {
                if (it != null ) {// Fetches the function from the Viewmodel to display data
                    recentPurchase2Total.text = it.toString()
                }else
                    recentPurchase2Total.text = ""
            }
            viewModel.thirdLatestEntryStore.observe(viewLifecycleOwner) {
                if (it != null ) {// Fetches the function from the Viewmodel to display data
                    recentPurchase3Store.text = it.toString()
                }else
                    recentPurchase3Store.text = ""
            }
            viewModel.thirdLatestEntryTotal.observe(viewLifecycleOwner) {
                if (it != null ) {// Fetches the function from the Viewmodel to display data
                    recentPurchase3Total.text = it.toString()
                }else
                    recentPurchase3Total.text = ""
            }
            viewModel.fourthLatestEntryStore.observe(viewLifecycleOwner) {
                if (it != null ) {// Fetches the function from the Viewmodel to display data
                    recentPurchase4Store.text = it.toString()
                }else
                    recentPurchase4Store.text = ""
            }
            viewModel.fourthLatestEntryTotal.observe(viewLifecycleOwner) {
                if (it != null ) {// Fetches the function from the Viewmodel to display data
                    recentPurchase4Total.text = it.toString()
                }else
                    recentPurchase4Total.text = ""
            }
            monthlyBudgetBtn.apply {
                setOnClickListener {
                    showMessageBox()
                }
            }
        }
        calculatePercent()
        changeProgress()
        setBudget()
        return view
    }
    fun showMessageBox(){

        val mainView = requireActivity().layoutInflater.inflate(R.layout.fragment_monthly_expense, null)
        seekBar = mainView.findViewById(R.id.seekBar)
        textView4 = mainView.findViewById(R.id.textView4)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {


            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                textView4.text = "$progress €"   // Display the current progress of SeekBar
                monthlyBudget = progress
                calculatePercent()
                changeProgress()
                setBudget()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        android.app.AlertDialog.Builder(context)
            .setView(mainView)
            .setMessage("Set your monthly budget")
            .setPositiveButton("Confirm") { _, _ -> monthlyBudgetText.text = monthlyBudget.toString()}
            .show()
    }
     fun calculatePercent(){
    purchaseViewmodel.totalSum.observe(viewLifecycleOwner)
        {
            monthlyPercent.text = (it.toDouble()/monthlyBudget.toDouble()*100).toInt().toString()
        }
    }

    fun changeProgress(){
        purchaseViewmodel.totalSum.observe(viewLifecycleOwner)
        {
            budgetProgressBar.progress = (it.toDouble()/monthlyBudget.toDouble()*100).toInt()
        }
    }
    fun setBudget(){
        monthlyBudgetText.text = monthlyBudget.toString()
    }

}