package com.example.myanmarlotteryadmin.ui.home.viewpost

import android.annotation.SuppressLint
import com.example.myanmarlotteryadmin.R
import com.example.myanmarlotteryadmin.data.model.Post
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_post_list.*

class DisplayPost(
    private val post : Post
): Item() {
    @SuppressLint("SetTextI18n")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.txt_view_position.text = "${position+1}"
        viewHolder.txt_view_char.text = post.mChar
        viewHolder.txt_view_number.text = post.num.toString()
        viewHolder.txt_view_ticket.text = "${post.ticket}ေစာင်"
    }

    override fun getLayout(): Int {
        return R.layout.item_post_list
    }

}