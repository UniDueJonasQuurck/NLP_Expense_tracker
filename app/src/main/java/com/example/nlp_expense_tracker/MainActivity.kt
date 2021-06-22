package com.example.nlp_expense_tracker

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.nlp_expense_tracker.fragments.Home.HomeFragment
import com.example.nlp_expense_tracker.fragments.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

import com.example.nlp_expense_tracker.fragments.Camera.Scanner
import com.example.nlp_expense_tracker.fragments.History.HistoryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)
        tabs = findViewById(R.id.tabs)
        setUpTabs()
    }

    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(),"")
        adapter.addFragment(Scanner(),"")
        adapter.addFragment(HistoryFragment(),"")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_home_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_show_chart_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_add_shopping_cart_24)
    }

}

const val ADD_RECEIPT_RESULT_OK = Activity.RESULT_FIRST_USER