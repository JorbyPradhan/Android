package com.example.myanmarlottery.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myanmarlottery.R
import com.example.myanmarlottery.data.model.LotteryChar
import com.example.myanmarlottery.data.repository.HomeRepo
import com.example.myanmarlottery.util.Coroutines
import com.example.myanmarlottery.util.snackBar
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var factory: HomeViewModelFactory
    private lateinit var navController: NavController
    private val regionList = listOf<String>("Yangon")
    private val townList = arrayListOf<String>("Ahlon","Bahan","Dagon","Hlaing",
            "Kamayut","Kyauktada","Kyimyindaing","Lanmadaw","Latha","Mayangon","Pabedan","Sanchaung",
            "Botataung","Dagon Seikkan","Dawbon","Mingala Taungnyunt","New Dagon East","New Dagon North",
            "New Dagon South","North Okkalapa","Pazundaung","South Okkalapa","Tamwe","Thaketa",
            "Thingangyun","Yankin","Cocokyun","Dala","Kawhmu","Khayan","Kungyangon","Kyauktan",
            "Seikkyi Kanaungto","Thanlyin","Thongwa","Twante","Hlaingthaya East","Hlaingthaya West",
            "Hlegu","Hmawbi","Htantabin","Insein","Mingaladon", "Shwepyitha","Taikkyi")

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        /* val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        factory = HomeViewModelFactory(HomeRepo())
        homeViewModel =
            ViewModelProvider(this,factory).get(HomeViewModel::class.java)
        navController = Navigation.findNavController(homeLayout)
        val townAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,townList)
        Select_town.setAdapter(townAdapter)
        val regionAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1,regionList)
        Select_region.setAdapter(regionAdapter)
        Select_town.setOnClickListener {
            Select_town.showDropDown()
        }
        Select_town.setOnItemClickListener { parent, view, position, id ->
            Select_town.showDropDown()
        }
        Select_region.setOnClickListener {
            Select_region.showDropDown()
        }
        Select_region.setOnItemClickListener { parent, view, position, id ->
            Select_region.showDropDown()
        }

        bindChar()

    }

    private fun bindChar() = Coroutines.main {
        val list  = homeViewModel.mutableCharList.await()
        list.observe(viewLifecycleOwner, {
            initRecyclerView(it.toDisplayHomeItem())
        })
    }

    private fun initRecyclerView(toDisplayHomeItem: List<HomeDisplay>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(toDisplayHomeItem)
        }
        rec_list.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.setOnItemClickListener { item, _ ->
            val items = item as HomeDisplay
            homeLayout.snackBar("You Clicked ${items.lotteryChar.mChar}")
            val bundle = Bundle()
            bundle.putString("MChar",items.lotteryChar.mChar)
            //pass township
            bundle.putString("townShip",Select_town.text.toString().trim())
            bundle.putString("region",Select_region.text.toString().trim())
            bundle.putStringArrayList("townShipList",townList)
            navController.navigate(R.id.action_nav_home_to_viewLotteryNumberFragment,bundle)
        }
    }

    private fun List<LotteryChar>.toDisplayHomeItem(): List<HomeDisplay> {
        return this.map {
            HomeDisplay(it)
        }

    }
}

