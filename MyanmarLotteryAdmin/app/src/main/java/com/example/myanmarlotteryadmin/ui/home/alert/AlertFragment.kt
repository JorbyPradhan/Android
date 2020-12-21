package com.example.myanmarlotteryadmin.ui.home.alert

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myanmarlotteryadmin.R
import com.example.myanmarlotteryadmin.data.repository.AlertRepository
import com.example.myanmarlotteryadmin.domain.AlertImpl
import com.example.myanmarlotteryadmin.ui.home.alert.alertDetail.ItemListDialogFragment
import com.example.myanmarlotteryadmin.util.Coroutines
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.alert_fragment.*

class AlertFragment : Fragment() {

    companion object {
        fun newInstance() = AlertFragment()
    }

    private lateinit var viewModel: AlertViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.alert_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = AlertViewModelFactory(AlertImpl(AlertRepository()))
        viewModel = ViewModelProvider(this,factory).get(AlertViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        val alert = viewModel.list.await()
        alert.observe(viewLifecycleOwner,{
            initRecyclerAlert(it.toAlertList())
        })
    }

    private fun initRecyclerAlert(toAlertList: List<AlertDisplay>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(toAlertList)
        }
        rec_alert.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.setOnItemClickListener { item, view ->
            ItemListDialogFragment.newInstance(5).show(childFragmentManager,"Hi")
        }
    }

    private fun List<String>.toAlertList():List<AlertDisplay>{
        return this.map {
            AlertDisplay(it)
        }
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.app_bar_search)
        if (item != null)
            item.isVisible = false
    }

}