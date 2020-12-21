package com.example.myanmarlotteryadmin.ui.home.viewpost

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
import com.example.myanmarlotteryadmin.util.Coroutines
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.display_fragment.*

class DisplayFragment : Fragment() {

    companion object {
        fun newInstance() = DisplayFragment()
    }

    private lateinit var viewModel: DisplayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.display_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = DisplayViewModelFactory(HomeInterfaceImpl(HomeRepository()))
        viewModel = ViewModelProvider(this,factory).get(DisplayViewModel::class.java)
        bindLottery()
    }
    private fun bindLottery() = Coroutines.main {
        val lotto = viewModel.lotteryList.await()
        lotto.observe(viewLifecycleOwner, {
            initRecyclerList(it.toShowList())
        })
    }

    private fun initRecyclerList(toShowList: List<DisplayPost>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(toShowList)
        }
        rec_post.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun List<Post>.toShowList(): List<DisplayPost> {
        return this.map {
            DisplayPost(it)
        }

    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.app_bar_search)
        if (item != null)
            item.isVisible = false
    }

}