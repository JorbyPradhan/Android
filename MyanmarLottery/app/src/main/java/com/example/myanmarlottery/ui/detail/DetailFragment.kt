package com.example.myanmarlottery.ui.detail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myanmarlottery.R
import com.example.myanmarlottery.data.model.LottoModel
import com.example.myanmarlottery.data.repository.NumberRepo
import com.example.myanmarlottery.ui.lottery.LotteryDisplay
import com.example.myanmarlottery.util.Coroutines
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.show_user_alert_fragment.*
import kotlinx.android.synthetic.main.view_lottery_number_fragment.*

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var ss : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = DetailFactory(NumberRepo())
        viewModel = ViewModelProvider(this,factory).get(DetailViewModel::class.java)
        ss = arguments?.getString("Mychar").toString()
        val number = arguments?.getInt("number",0)!!
        val num = arguments?.getInt("ticket",0)!!
        viewModel.num = number.toString()
        viewModel.ticket = num.toString()
        detail_tic.text = "${num}စောင်"
        detail_price.text = "${num*1200}"
        bindDetail()

    }

    private fun bindDetail()=Coroutines.main {
        val numberList = viewModel.numbers.await()
        numberList.observe(viewLifecycleOwner, {
            initRecycler(it.toDetailNumber())
        })
    }

    private fun initRecycler(toDetailNumber:  List<DetailDisplay>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(toDetailNumber)
        }
        detail_rec.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }
    private fun List<LottoModel>.toDetailNumber(): List<DetailDisplay> {
        return this.map {
            DetailDisplay(ss,it)
        }

    }
}