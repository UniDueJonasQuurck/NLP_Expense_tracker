package com.example.nlp_expense_tracker.fragments

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.nlp_expense_tracker.R

class HomeFragment : Fragment() {

    private lateinit var monthlyExpense: TextView
    private lateinit var recentPurchase1: TextView
    private lateinit var recentPurchase2: TextView
    private lateinit var recentPurchase3: TextView
    private lateinit var recentPurchase4: TextView
    private lateinit var viewPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View {val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        monthlyExpense = view.findViewById(R.id.monthlyExpense)
        viewPager = requireActivity().findViewById(R.id.viewPager)
        recentPurchase1 = view.findViewById(R.id.recentPurchase1)
        recentPurchase2 = view.findViewById(R.id.recentPurchase2)
        recentPurchase3 = view.findViewById(R.id.recentPurchase3)
        recentPurchase4 = view.findViewById(R.id.recentPurchase4)

        recentPurchase1.setOnClickListener {
            viewPager.setCurrentItem(1)
        }
        recentPurchase2.setOnClickListener {
            viewPager.setCurrentItem(1)
        }
        recentPurchase3.setOnClickListener {
            viewPager.setCurrentItem(1)
        }
        recentPurchase4.setOnClickListener {
            viewPager.setCurrentItem(1)
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