package com.example.myanmarlottery.ui.alert

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myanmarlottery.R
import com.example.myanmarlottery.data.model.LottoModel
import com.example.myanmarlottery.data.repository.OrderRepo
import com.example.myanmarlottery.util.Coroutines
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.show_user_alert_fragment.*

class ShowUserAlertFragment : Fragment() {

    companion object {
        fun newInstance() = ShowUserAlertFragment()
    }

    private lateinit var viewModel: ShowUserAlertViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.show_user_alert_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ShowUserViewModelFactory(OrderRepo())
        viewModel = ViewModelProvider(this,factory).get(ShowUserAlertViewModel::class.java)
        bindOrder()
    }

    private fun bindOrder() = Coroutines.main {
        val orderList = viewModel.numbers.await()
        orderList.observe(viewLifecycleOwner, {
            initRecyclerOrder(it.toOrderList())
        })
    }

    private fun initRecyclerOrder(toOrderList: List<ShowOrder>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(toOrderList)
        }
        rec_order.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun List<LottoModel>.toOrderList(): List<ShowOrder> {
        return this.map {
            ShowOrder(it)
        }

    }
}
