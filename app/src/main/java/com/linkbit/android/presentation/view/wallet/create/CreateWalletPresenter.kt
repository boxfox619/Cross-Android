package com.linkbit.android.presentation.view.wallet.create

import android.util.Log
import com.linkbit.android.R
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.data.repository.CoinRepository
import com.linkbit.android.data.repository.WalletRepository
import com.linkbit.android.entity.CoinModel
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.helper.ToastHelper
import com.linkbit.android.presentation.base.Presenter


class CreateWalletPresenter(
        view: CreateWalletView,
        private val coinRepository: CoinRepository = CoinRepository(view.getContext()),
        private val walletRepository: WalletRepository = WalletRepository(view.getContext())
) : Presenter<CreateWalletView>(view) {

    lateinit var wallet: WalletEditModel
    lateinit var supportedCoins: ArrayList<CoinModel>
    lateinit var resultWallet: WalletModel
    var step: Int = -1
    var canNext: Boolean = false

    fun canNext(state: Boolean) {
        canNext = state
        view.nextButtonEnabled(state)
    }

    fun onNext() {
        step += 1
        view.setStep(step)
        this.canNext(false)
    }

    fun init() {
        this.wallet = WalletEditModel()
        coinRepository.getSupportCoins().subscribe({
            this.supportedCoins = it as ArrayList<CoinModel>
            this.onNext()
        },{
            Log.d("CreateWallet", "Fail get Supported coins")})
    }

    fun doCreate(){
        this.view.setProgressDialogVisible(true)
        this.walletRepository.createWallet(this.wallet).subscribe({
            this.resultWallet = it
            this.walletRepository.loadWalletList().subscribe({
                this.view.setProgressDialogVisible(false)
                this.onNext()
            },{
                this.view.setProgressDialogVisible(false)
                ToastHelper.showToast(this.view.getContext(), R.string.err_create_wallet_fail)
                //@TODO Implement create wallet error handling
            })
        }, {
            this.view.setProgressDialogVisible(false)
            ToastHelper.showToast(this.view.getContext(), R.string.err_create_wallet_fail)
            //@TODO Implement create wallet error handling
        })
    }
}

