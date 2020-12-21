package com.example.myanmarlotteryadmin.ui.password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myanmarlotteryadmin.R

class ChangePasswordFragment : Fragment() {

    private lateinit var slideshowViewModel: ChangePasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProviders.of(this).get(ChangePasswordViewModel::class.java)

        return inflater.inflate(R.layout.fragment_slideshow, container, false)
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.app_bar_search)
        if (item != null)
            item.isVisible = false
    }
}