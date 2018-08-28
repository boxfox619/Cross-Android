package com.linkbit.android.helper
import android.content.*
import android.widget.*

object Helper {

    fun showToast(context: Context, msg: String) = Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

    private fun getPref(context: Context) = context.getSharedPreferences("cross_pref", Context.MODE_PRIVATE)

    fun getPrefData(key: String, context: Context): String = getPref(context).getString(key, "")

    fun setPrefData(key: String, value: String, context: Context) = getPref(context).edit().putString(key, value).apply()

}