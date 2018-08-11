package com.linkbit.android.data.repository

import android.content.Context
import com.linkbit.android.data.model.coin.CoinEntityMapper
import com.linkbit.android.data.model.coin.CoinModel
import com.linkbit.android.data.model.coin.CoinObject
import com.linkbit.android.data.model.coin.CoinRepository
import com.linkbit.android.data.network.Response
import com.linkbit.android.data.network.retrofit
import io.reactivex.Observable
import rx.subjects.BehaviorSubject

class CoinNetworkRepository(private val context: Context) : CoinRepository {

    val coinList: BehaviorSubject<List<CoinModel>> = BehaviorSubject.create()

    override fun getSupportCoins(): Observable<List<CoinModel>> {
        return Observable.create {
            val subscriber = it
            context.retrofit.walletAPI.getSupportedCoins().enqueue((object : Response<List<CoinObject>>(context) {
                override fun setResponseData(code: Int, newWalletList: List<CoinObject>?) {
                    if (isSuccess(code) && newWalletList != null) {
                        val loadedCoinList: List<CoinModel> = newWalletList.map { it -> CoinEntityMapper.fromNetworkObject(it) }
                        coinList.onNext(loadedCoinList)
                        subscriber.onNext(loadedCoinList)
                        subscriber.onComplete()
                    } else {
                        subscriber.onError(null)
                    }
                }
            }))
        }
    }

    override fun getCoinByName(name: String): Observable<CoinModel> {
        return Observable.create { obs ->
            getSupportCoins().subscribe { coinList ->
                val result = coinList.filter { it.name.equals(name) }
                if (result.isNotEmpty())
                    obs.onNext(result[0])
                else obs.onNext(null)
            }
        }
    }

    override fun getCoinBySymbol(symbol: String): Observable<CoinModel> {
        return Observable.create { obs ->
            getSupportCoins().subscribe { coinList ->
                val result = coinList.filter { it.symbol.equals(symbol) }
                if (result.isNotEmpty())
                    obs.onNext(result[0])
                else obs.onNext(null)
            }
        }
    }

    override fun getCoinIcon(coin: CoinModel): Observable<String> {

    }

}