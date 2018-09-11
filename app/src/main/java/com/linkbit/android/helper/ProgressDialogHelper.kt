package com.linkbit.android.helper

import android.app.ProgressDialog
import android.content.*

object ProgressDialogHelper {

    fun show(ctx: Context, msg: Int, cancelable: Boolean) : ProgressDialog{
        return this.show(ctx, "", ctx.getString(msg), cancelable)
    }

    fun show(ctx: Context, msg: String, cancelable: Boolean) : ProgressDialog{
        return this.show(ctx, "", msg, cancelable)
    }

    fun show(ctx: Context, title:Int, msg: Int, cancelable: Boolean) : ProgressDialog{
        return this.show(ctx, ctx.getString(title), ctx.getString(msg), cancelable)
    }

    fun show(ctx: Context, title:String, msg: String, cancelable: Boolean) : ProgressDialog{
        val progressDialog = ProgressDialog(ctx)
        progressDialog.setTitle(title)
        progressDialog.setMessage(msg)
        progressDialog.setCancelable(cancelable)
        progressDialog.show()
        return progressDialog
    }

}