package com.example.myanmarlotteryadmin.ui.home.addpost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myanmarlotteryadmin.R
import com.example.myanmarlotteryadmin.data.model.Post
import com.example.myanmarlotteryadmin.data.repository.HomeRepository
import com.example.myanmarlotteryadmin.domain.HomeInterfaceImpl
import com.example.myanmarlotteryadmin.util.Coroutines
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.add_fragment.*

class AddFragment : Fragment() {

    companion object {
        fun newInstance() = AddFragment()
    }

    private lateinit var viewModel: AddViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = AddViewModelFactory(HomeInterfaceImpl(HomeRepository()))
        viewModel = ViewModelProvider(this,factory).get(AddViewModel::class.java)
        navController = Navigation.findNavController(scrollLayout)
        bindLottery()
    }

    private fun bindLottery() = Coroutines.main {
        val lotto = viewModel.lotteryList.await()
        lotto.observe(viewLifecycleOwner, {
            initRecyclerList(it.toAddShowList())
        })
    }

    private fun initRecyclerList(toAddShowList: List<AddPostDisplay>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(toAddShowList)
        }
        rec_digit.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.setOnItemClickListener { item, view ->
            val itemList = item as AddPostDisplay
            val bundle = Bundle()
            bundle.putString("mChar",itemList.post.mChar)
            bundle.putInt("number",itemList.post.num)
            navController.navigate(R.id.action_nav_home_to_addDetailFragment2,bundle)
        }
    }

    private fun List<Post>.toAddShowList(): List<AddPostDisplay> {
        return this.map {
            AddPostDisplay(it)
        }

    }

}