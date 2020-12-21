package com.example.myanmarlotteryadmin.ui.phone

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myanmarlotteryadmin.R

class ChangePhoneFragment : Fragment() {

    companion object {
        fun newInstance() = ChangePhoneFragment()
    }

    private lateinit var viewModel: ChangePhoneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.change_phone_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChangePhoneViewModel::class.java)
        // TODO: Use the ViewModel
    }

}