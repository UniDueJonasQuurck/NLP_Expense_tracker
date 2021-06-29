package com.example.nlp_expense_tracker.fragments.Home

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.RelativeSizeSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.example.nlp_expense_tracker.R
import com.example.nlp_expense_tracker.databinding.FragmentHistoryBinding
import com.example.nlp_expense_tracker.databinding.FragmentHomeBinding
import com.example.nlp_expense_tracker.fragments.History.PurchaseViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var monthlyExpense: TextView

    private lateinit var viewPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        monthlyExpense = view.findViewById(R.id.monthlyExpense)
        viewPager = requireActivity().findViewById(R.id.viewPager)

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
            viewModel.latestEntryStore.observe(viewLifecycleOwner) { // Fetches the function from the Viewmodel to display data
                recentPurchase1Store.text = it.toString()
            }
            viewModel.latestEntryTotal.observe(viewLifecycleOwner) {
                recentPurchase1Total.text = it.toString()
                if (recentPurchase1Store.text == "Lidl") {
                    recentPurchase1Total.setBackgroundColor(Color.BLUE)
                }
                else if (recentPurchase1Store.text == "Aldi") {
                    recentPurchase1Total.setBackgroundColor(Color.rgb(216,27,96))
                }
                else if (recentPurchase1Store.text == "Rewe") {
                    recentPurchase1Total.setBackgroundColor(Color.rgb(216,27,96))
                }
                else if (recentPurchase1Store.text == "Edeka") {
                    recentPurchase1Total.setBackgroundColor(Color.rgb(216,27,96))
                }
                else if (recentPurchase1Store.text == "Kaufland") {
                    recentPurchase1Total.setBackgroundColor(Color.rgb(216,27,96))
                }
            }
            viewModel.secondLatestEntryStore.observe(viewLifecycleOwner) {
                recentPurchase2Store.text = it.toString()
            }
            viewModel.secondLatestEntryTotal.observe(viewLifecycleOwner) {
                recentPurchase2Total.text = it.toString()
            }
            viewModel.thirdLatestEntryStore.observe(viewLifecycleOwner) {
                recentPurchase3Store.text = it.toString()
            }
            viewModel.thirdLatestEntryTotal.observe(viewLifecycleOwner) {
                recentPurchase3Total.text = it.toString()
            }
            viewModel.fourthLatestEntryStore.observe(viewLifecycleOwner) {
                recentPurchase4Store.text = it.toString()
            }
            viewModel.fourthLatestEntryTotal.observe(viewLifecycleOwner) {
                recentPurchase4Total.text = it.toString()
            }
        }

        textTwoSpan()
        // Inflate the layout for this fragment
        return view
    }
    private fun textTwoSpan() {

        val spannableString = SpannableString("Monthly budget\n You have already spent 76% of your monthly expense limit")
        val sizeSpan = RelativeSizeSpan(2f)
        spannableString.setSpan(sizeSpan,0,14, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        monthlyExpense.text=spannableString
    }
}