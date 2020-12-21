package com.example.myanmarlotteryadmin.ui.home.addpost

import com.example.myanmarlotteryadmin.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.add_detail.*

class DetailPost(
    private val mChar : String,
    private val num : Int
):Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.txt_position.text = (position+1).toString()
        viewHolder.txt_char.text = mChar
        viewHolder.txt_number.text = num.toString()
    }

    override fun getLayout(): Int {
        return R.layout.add_detail
    }
}