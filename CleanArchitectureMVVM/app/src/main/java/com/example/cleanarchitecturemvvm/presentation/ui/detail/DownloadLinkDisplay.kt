package com.example.cleanarchitecturemvvm.presentation.ui.detail

import com.example.cleanarchitecturemvvm.R
import com.example.cleanarchitecturemvvm.data.model.DownloadLink
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.download_video.*
import kotlinx.android.synthetic.main.movie_item.*

class DownloadLinkDisplay(
     val download : DownloadLink
):Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.txt_download_link.text = download.link
    }

    override fun getLayout(): Int {
        return R.layout.download_video
    }

}