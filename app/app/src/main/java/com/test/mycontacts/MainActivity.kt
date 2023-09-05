package com.test.mycontacts


import android.os.Bundle
import com.test.mycontacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)

        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        val pager = binding.viewPager
        pager.adapter = pagerAdapter
        val tab = binding.tab
        tab.setupWithViewPager(pager)
    }
}
