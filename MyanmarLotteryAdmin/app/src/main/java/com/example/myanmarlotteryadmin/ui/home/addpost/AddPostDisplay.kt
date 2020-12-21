package com.example.myanmarlotteryadmin.ui.home.addpost

import android.annotation.SuppressLint
import com.example.myanmarlotteryadmin.R
import com.example.myanmarlotteryadmin.data.model.Post
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_list.*


class AddPostDisplay(
    val post : Post
): Item() {
    @SuppressLint("SetTextI18n")
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.txt_sys_number.text = "${post.mChar} ${post.num}"
    }

    override fun getLayout(): Int {
        return R.layout.item_list
    }

}