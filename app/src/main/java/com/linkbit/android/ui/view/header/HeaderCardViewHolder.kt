package com.linkbit.android.ui.view.header;

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.linkbit.android.R
import com.linkbit.android.ui.base.BaseViewHolder
import java.text.DecimalFormat

class HeaderCardViewHolder(adapter: RecyclerView.Adapter<*>, itemView: View) : BaseViewHolder<HeaderCardViewModel>(adapter, itemView) {

    override fun onViewHolder(item: HeaderCardViewModel?, position: Int) {
        itemView.findViewById<TextView>(R.id.tv_coin_name).text = item?.name
        itemView.findViewById<TextView>(R.id.tv_coin_symbol).text = item?.symbol
        itemView.findViewById<TextView>(R.id.tv_coin_money).text = item?.money
        itemView.findViewById<TextView>(R.id.tv_statistic_balance).text = String.format("%.2f", item?.balance)
        itemView.findViewById<TextView>(R.id.tv_statistic_price).text = DecimalFormat("#,###").format(item?.price)
        var url = String.format("%s/assets/%s.png", context!!.getString(R.string.host), item?.symbol?.toUpperCase())
        Glide.with(context!!).load(url).into(itemView.findViewById(R.id.iv_statistic_icon))
    }
}
