package com.example.nlp_expense_tracker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.nlp_expense_tracker.fragments.HistoryFragment
import com.example.nlp_expense_tracker.fragments.HomeFragment
import com.example.nlp_expense_tracker.fragments.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

import com.example.nlp_expense_tracker.fragments.Scanner
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

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
        adapter.addFragment(HistoryFragment(),"")
        adapter.addFragment(Scanner(),"")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_home_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_show_chart_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_add_shopping_cart_24)
    }

}