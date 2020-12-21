package com.example.myanmarlotteryadmin.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myanmarlotteryadmin.ui.home.addpost.AddFragment
import com.example.myanmarlotteryadmin.ui.home.alert.AlertFragment
import com.example.myanmarlotteryadmin.ui.home.viewpost.DisplayFragment
import com.example.myanmarlotteryadmin.ui.order.OrderListFragment

@Suppress("DEPRECATION")
class MyAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                AddFragment.newInstance()
            }
            1 -> {
                DisplayFragment.newInstance()
            }
            2 ->{
                OrderListFragment.newInstance()
            }
            3 ->{
                AlertFragment.newInstance()
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}