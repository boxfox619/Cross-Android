package com.linkbit.android.presentation.wallet.create

import android.util.Log
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.data.repository.CoinRepository
import com.linkbit.android.data.repository.WalletRepository
import com.linkbit.android.entity.CoinModel
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.Presenter


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
    }
}

