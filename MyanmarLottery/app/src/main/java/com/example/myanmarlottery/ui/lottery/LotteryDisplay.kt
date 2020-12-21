package com.example.myanmarlottery.ui.lottery

import android.annotation.SuppressLint
import com.example.myanmarlottery.R
import com.example.myanmarlottery.data.model.LottoModel
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.digit_lst.*


class LotteryDisplay (
    val mChar : String,
    val lotto : LottoModel
): Item(){
    @SuppressLint("SetTextI18n")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.txt_number.text = "$mChar ${lotto.number}"
        viewHolder.txt_total_ticket.text = "${lotto.totalTicket}စောင်"
        viewHolder.to_number.text = "/${lotto.toNumber}"
    }

    override fun getLayout() = R.layout.digit_lst

}