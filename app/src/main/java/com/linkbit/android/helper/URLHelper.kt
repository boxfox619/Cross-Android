package com.linkbit.android.helper

import android.content.Context
import com.linkbit.android.R

object URLHelper {


    fun createAssetUrl(ctx: Context, symbol: String): String{
        val serverHost = ctx.getString(R.string.server_host)
        val asset = ctx.getString(R.string.url_asset)
        var url = String.format("%s%s%s.png", serverHost, asset, symbol?.toUpperCase())
        return url
    }
}