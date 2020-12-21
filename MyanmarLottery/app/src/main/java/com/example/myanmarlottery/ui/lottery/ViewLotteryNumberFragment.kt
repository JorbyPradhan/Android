package com.example.myanmarlottery.ui.lottery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myanmarlottery.R
import com.example.myanmarlottery.data.model.LottoModel
import com.example.myanmarlottery.data.repository.NumberRepo
import com.example.myanmarlottery.ui.home.HomeDisplay
import com.example.myanmarlottery.util.Coroutines
import com.example.myanmarlottery.util.snackBar
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_lottery_number_fragment.*

class ViewLotteryNumberFragment : Fragment() {

    companion object {
        fun newInstance() = ViewLotteryNumberFragment()
    }

    private lateinit var viewModel: ViewLotteryNumberViewModel
    private lateinit var myChar: String
    private lateinit var town: String
    private lateinit var region: String
    private lateinit var navController: NavController
    private var townShipList: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myChar = arguments?.getString("MChar", null).toString()
        town = arguments?.getString("townShip", null).toString()
        region = arguments?.getString("region", null).toString()
        townShipList = arguments?.getStringArrayList("townShipList")!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_lottery_number_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = LotteryFactory(NumberRepo())
        viewModel = ViewModelProvider(this, factory).get(ViewLotteryNumberViewModel::class.java)
        navController = Navigation.findNavController(navLayout)
        change_town.setText(town)
        val townAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            townShipList
        )
        change_town.setAdapter(townAdapter)
        change_char.setText(myChar)
        change_town.setOnClickListener {
            change_town.showDropDown()
        }
        change_town.setOnItemClickListener { parent, view, position, id ->
            change_town.showDropDown()
        }
        // TODO: Use the ViewModel
        bindNumber()
    }

    private fun bindNumber() = Coroutines.main {
        val numberList = viewModel.numbers.await()
        numberList.observe(viewLifecycleOwner, {
            initRecycler(it.toDisplayNumber())
        })
    }

    private fun initRecycler(toDisplayNumber: List<LotteryDisplay>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(toDisplayNumber)
        }
        rec_digit.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.setOnItemClickListener { item, _ ->
            val items = item as LotteryDisplay
            val bundle = Bundle()
            bundle.putInt("ticket",items.lotto.totalTicket)
            bundle.putString("Mychar",items.mChar)
            bundle.putInt("number",items.lotto.number)
            //pass township
           // bundle.putString("townShip",Select_town.text.toString().trim())
            //bundle.putString("region",Select_region.text.toString().trim())
            //bundle.putStringArrayList("townShipList",townList)
            navController.navigate(R.id.action_viewLotteryNumberFragment_to_detailFragment,bundle)
        }
    }

    private fun List<LottoModel>.toDisplayNumber(): List<LotteryDisplay> {
        return this.map {
            LotteryDisplay(myChar, it)
        }

    }

}