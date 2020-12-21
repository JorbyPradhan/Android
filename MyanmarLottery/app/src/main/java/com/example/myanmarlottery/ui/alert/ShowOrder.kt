package com.example.myanmarlottery.ui.alert

import android.annotation.SuppressLint
import com.example.myanmarlottery.R
import com.example.myanmarlottery.data.model.LottoModel
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.order_layout.*

class ShowOrder(
    private val order : LottoModel
) : Item(){
    @SuppressLint("SetTextI18n")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.order_ticket.text = "${order.totalTicket}စောင်"
        viewHolder.order_number.text = "${order.myChar} ${order.number}"
    }

    override fun getLayout() = R.layout.order_layout

}