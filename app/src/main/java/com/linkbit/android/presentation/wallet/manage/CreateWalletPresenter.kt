package com.linkbit.android.presentation.wallet.manage

import android.util.Log
import com.linkbit.android.data.model.wallet.WalletEditModel
import com.linkbit.android.data.repository.CoinRepository
import com.linkbit.android.entity.CoinModel
import com.linkbit.android.presentation.Presenter


class CreateWalletPresenter(
        view: CreateWalletView,
        private val coinRepository: CoinRepository = CoinRepository(view.getContext())
) : Presenter<CreateWalletView>(view) {

    lateinit var wallet: WalletEditModel
    lateinit var supportedCoins: ArrayList<CoinModel>
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
}

