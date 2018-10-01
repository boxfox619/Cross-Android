package com.linkbit.android.data.repository

import android.content.Context
import com.linkbit.android.data.model.transaction.TransactionResult
import com.linkbit.android.data.model.wallet.WalletDataRealmObject
import com.linkbit.android.data.network.Response
import com.linkbit.android.data.network.retrofit
import com.linkbit.android.domain.WithdrawUsecase
import com.linkbit.android.entity.TransactionModel
import com.linkbit.android.helper.realm
import rx.Single

class WithdrawRepository(private val context: Context,
                         private val transactionRepository: TransactionRepository = TransactionRepository(context)) : WithdrawUsecase {

    override fun withdraw(symbol:String, accountAddress: String, password: String, targetAddress: String, amount: Double): Single<TransactionModel> {
        return Single.create { subscriber ->
            val walletData = context.realm.where(WalletDataRealmObject::class.java).equalTo("accountAddress", accountAddress).findFirst()
            context.retrofit.withdrawAPI.withdraw(symbol, walletData.walletFileName, walletData.walletData, password, targetAddress, amount.toString()).enqueue(object : Response<TransactionResult>(context) {
                override fun setResponseData(code: Int, transactionResult: TransactionResult?) {
                    if (isSuccess(code) && transactionResult != null && transactionResult.isStatus) {
                        transactionRepository.getTransactionByHash(transactionResult.transactionHash!!).subscribe ({
                            subscriber.onSuccess(it)
                        },{
                            subscriber.onError(Throwable("transaction load fail"))
                        })
                    } else {
                        subscriber.onError(Throwable("withdraw fail"))
                    }
                }
            })
        }
    }

}