package com.example.picdemo.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.picdemo.R
import com.example.picdemo.bean.PicListItem


class MainActivityAdapter(var layout: Int = R.layout.item_rcv_pic) :
        BaseQuickAdapter<PicListItem, BaseViewHolder>(layout){
    override fun convert(helper: BaseViewHolder, item: PicListItem) {

        helper.setText(R.id.tv_title, item.title)
        Glide.with(mContext).load(item._thumb).into(helper.getView<ImageView>(R.id.iv_photo))
    }


}