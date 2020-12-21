package com.example.myanmarlottery.ui.home

import com.example.myanmarlottery.R
import com.example.myanmarlottery.data.model.LotteryChar
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.feed_item.*

class HomeDisplay(
    val lotteryChar : LotteryChar
) : Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.txt_char.text = lotteryChar.mChar
    }

    override fun getLayout() = R.layout.feed_item
}