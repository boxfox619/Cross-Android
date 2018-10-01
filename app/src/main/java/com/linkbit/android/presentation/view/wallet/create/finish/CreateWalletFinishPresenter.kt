package com.linkbit.android.presentation.view.wallet.create.finish

import com.linkbit.android.data.repository.WalletRepository
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.base.Presenter
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context


class CreateWalletFinishPresenter(
        view: CreateWalletFinishView,
        private val wallet: WalletModel,
        private val walletRepository: WalletRepository = WalletRepository(view.getContext())
) : Presenter<CreateWalletFinishView>(view) {

    fun createQRCode(){
        this.walletRepository.createQRCode(this.wallet.accountAddress).subscribe{
            //@TODO Somthing
        }
    }

    fun createUrl(){

    }

    fun copyAddress() {
        val clipboard = ctx.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("address", this.wallet.accountAddress)
        clipboard.primaryClip = clip
    }

}

