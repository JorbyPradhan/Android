package com.example.myanmarlottery.ui.detail

import android.annotation.SuppressLint
import com.example.myanmarlottery.R
import com.example.myanmarlottery.data.model.LottoModel
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.detail_layout.*

class DetailDisplay(
    private val s : String,
    private val lotto : LottoModel
): Item() {
    @SuppressLint("SetTextI18n")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.txt_position.text = "${position+1}"
        viewHolder.detail_number.text = "$s ${lotto.number}"
    }

    override fun getLayout(): Int {
       return R.layout.detail_layout
    }
}