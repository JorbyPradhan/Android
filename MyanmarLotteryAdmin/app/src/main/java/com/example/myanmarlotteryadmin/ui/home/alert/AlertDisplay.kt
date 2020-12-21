package com.example.myanmarlotteryadmin.ui.home.alert


import com.example.myanmarlotteryadmin.R
import com.example.myanmarlotteryadmin.ui.home.alert.alertDetail.ItemListDialogFragment
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.alert_item.*

class AlertDisplay(
    private val alert : String
) :Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.txt_alert.text = alert
      /*  viewHolder.img_accept.setOnClickListener {
            ItemListDialogFragment.newInstance(5)
        }*/
    }

    override fun getLayout(): Int {
        return R.layout.alert_item
    }
}