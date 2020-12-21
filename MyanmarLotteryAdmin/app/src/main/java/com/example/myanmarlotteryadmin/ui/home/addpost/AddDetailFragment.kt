package com.example.myanmarlotteryadmin.ui.home.addpost

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myanmarlotteryadmin.R
import com.example.myanmarlotteryadmin.data.model.Post
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.add_detail_fragment.*


class AddDetailFragment : Fragment() {

    companion object {
        fun newInstance() = AddDetailFragment()
    }
    private var num : Int = 0
    private var pos = 0
    private lateinit var mChar: String
    private val numList : ArrayList<Int> = ArrayList()
    private val charList : ArrayList<String> = ArrayList()
    private lateinit var viewModel: AddDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_detail_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddDetailViewModel::class.java)
        num = arguments?.getInt("number", 0)!!
        mChar = arguments?.getString("mChar",null).toString()
        txt_detail_number.text = "$mChar $num"
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            //val radio: RadioButton = radioGroup.findViewById(checkedId)
            when (checkedId) {
                R.id.radioButton1 -> {
                    numLayout1.visibility = View.GONE
                    numLayout.visibility = View.GONE
                    setDatawithSameChar()
                }
                R.id.radioButton2 -> {
                    numLayout.visibility = View.GONE
                    numLayout1.visibility = View.GONE
                    dataWithDiffChar()
                }
                R.id.radioButton3 -> {
                    numLayout1.visibility = View.VISIBLE
                    numLayout.visibility = View.GONE
                    Toast.makeText(
                        context, " Coming Soon :" ,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                pos = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                pos = 0
            }

        }
    }

    private fun dataWithDiffChar() {
        when(pos){
            0 -> {
                charList.clear()
                charList.add("က")
            }
            1 -> {
                charList.clear()
                charList.add("က")
                charList.add("ခ")
            }
            2->{
                charList.clear()
                charList.add("က")
                charList.add("ခ")
                charList.add("ဂ")
            }
            3->{
                charList.clear()
                charList.add("က")
                charList.add("ခ")
                charList.add("ဂ")
                charList.add("ဃ")
                charList.add("င")
            }
            4->{
                charList.clear()
                charList.add("က")
                charList.add("ခ")
                charList.add("ဂ")
                charList.add("ဃ")
                charList.add("င")
                charList.add("စ")
                charList.add("ဆ")
                charList.add("ဇ")
                charList.add("ဈ")
                charList.add("ည")
            }
        }
        initRecData(charList.toCharList())
    }

    private fun initRecData(toDetailList: List<DetailPost>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(toDetailList)
        }
        rec_detail.apply {
            setHasFixedSize(true)
            adapter = mAdapter
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun setDatawithSameChar() {
       when(pos){
           0 -> {
               numList.clear()
               numList.add(num)
           }
           1 -> {
               numList.clear()
               numList.add(num)
               numList.add(num+1)
           }
           2->{
               numList.clear()
               numList.add(num)
               for (i in 0 until 2){
                   numList.add(num+i+1)
               }
           }
           3->{
               numList.clear()
               numList.add(num)
               for (i in 0 until 4){
                   numList.add(num+i+1)
               }
           }
           4->{
               numList.clear()
               numList.add(num)
               for (i in 0 until 9){
                   numList.add(num+i+1)
               }
           }
       }
        initRecNumber(numList.toNumList())
    }

    private fun initRecNumber(toNumList: List<DetailPost>) {
        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(toNumList)
        }
        rec_detail.apply {
            setHasFixedSize(true)
            adapter = mAdapter
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun List<String>.toCharList(): List<DetailPost> {
        return this.map {
            DetailPost(it,num)
        }

    }
    private fun List<Int>.toNumList(): List<DetailPost> {
        return this.map {
            DetailPost(mChar,it)
        }

    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.app_bar_search)
        if (item != null)
            item.isVisible = false
    }
}