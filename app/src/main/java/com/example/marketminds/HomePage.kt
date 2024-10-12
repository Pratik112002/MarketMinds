package com.example.marketminds

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.marketminds.databinding.ActivityHomePageBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomePage : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var myAdapter: MyPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize View Binding
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1 - Initialize the ViewPager
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.viewPager2.isUserInputEnabled=false

        // 2- Add Fragments to the List in the adapter class
        myAdapter = MyPagerAdapter(supportFragmentManager, lifecycle)
        myAdapter.addFragmentToList(StockFragment())
        myAdapter.addFragmentToList(NewsFragment())
        myAdapter.addFragmentToList(ProfileFragment())


        // 3- Connect adapter with ViewPager2
        binding.viewPager2.adapter = myAdapter

        // 4- Connecting TabLayout with ViewPager
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager2
        ) { tab, position ->
            // Customize the tab layout if needed
            when (position) {
                0 ->{ tab.setIcon(R.drawable.stock_icon)}

                1 ->{ tab.setIcon(R.drawable.news_icon)}

                2 -> {tab.setIcon(R.drawable.profile_icon)}

            }
        }.attach()
    }
}