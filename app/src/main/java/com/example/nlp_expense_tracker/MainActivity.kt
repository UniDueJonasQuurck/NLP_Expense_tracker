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
import java.io.File
import javax.inject.Inject


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
    companion object {

        /** Use external media if it is available, our app's file directory otherwise */
        fun getOutputDirectory(context: Context): File {
            val appContext = context.applicationContext
            val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
                File(it, appContext.resources.getString(R.string.app_name)).apply { mkdirs() }
            }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else appContext.filesDir
        }
    }
}