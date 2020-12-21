package com.example.myanmarlotteryadmin.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myanmarlotteryadmin.R
import com.example.myanmarlotteryadmin.data.model.Post
import com.example.myanmarlotteryadmin.data.repository.HomeRepository
import com.example.myanmarlotteryadmin.domain.HomeInterfaceImpl
import com.example.myanmarlotteryadmin.ui.home.viewpost.DisplayPost
import com.example.myanmarlotteryadmin.util.Coroutines
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.order_list_fragment.*

class OrderListFragment : Fragment() {

    companion object {
        fun newInstance() = OrderListFragment()
    }

    private lateinit var viewModel: OrderListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = OrderListFactory(HomeInterfaceImpl(HomeRepository()))
        viewModel = ViewModelProvider(this,factory).get(OrderListViewModel::class.java)
        bindLottery()
    }
    private fun bindLottery() = Coroutines.main {
        val lotto = viewModel.lotteryList.await()
        lotto.observe(viewLifecycleOwner, {
            initRecyclerList(it.toShowList())
        })
    }

    private fun initRecyclerList(toShowList: List<OrderPost>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(toShowList)
        }
        rec_order.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun List<Post>.toShowList(): List<OrderPost> {
        return this.map {
            OrderPost(it)
        }

    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.app_bar_search)
        if (item != null)
            item.isVisible = false
    }
}