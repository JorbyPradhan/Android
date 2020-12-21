package com.example.myanmarlotteryadmin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myanmarlotteryadmin.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tabLayout.addTab(tabLayout.newTab().setText("Add"))
        tabLayout.addTab(tabLayout.newTab().setText("Post"))
        tabLayout.addTab(tabLayout.newTab().setText("Order"))
        tabLayout.addTab(tabLayout.newTab().setText("Alert"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = MyAdapter(requireActivity(), childFragmentManager,
            tabLayout.tabCount)
        viewPager.adapter = adapter
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_baseline_post_add_24)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_baseline_home_24)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_baseline_shopping_cart_24)
        tabLayout.getTabAt(3)?.setIcon(R.drawable.ic_baseline_notifications_active_24)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}