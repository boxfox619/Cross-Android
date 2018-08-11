package com.linkbit.android.presentation.splash

import com.google.firebase.auth.FirebaseAuth
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.google.firebase.auth.FacebookAuthProvider
import com.linkbit.android.R
import com.linkbit.android.helper.Helper
import com.linkbit.android.presentation.Presenter
import com.linkbit.android.service.FriendService
import com.linkbit.android.service.WalletService
import io.realm.Realm
import rx.Observable


class SplashPresenter(view: SplashView) : Presenter<SplashView>(view), FacebookCallback<LoginResult> {

    override fun destory() {}

    fun loadInitializeData() {
        view.let {
            view.showProgress()
            Observable.just(WalletService.instance.loadTotalTransactionList(it.getContext()),
                    WalletService.instance.loadWalletList(it.getContext()),
                    FriendService.instance.loadFriendList(it.getContext()))
                    .subscribe({ view.finishSplash() },
                            {
                                view.hideProgress()
                                view.showErrorMessage(ctx.getString(R.string.err_fail_load_init_data))
                            })
        }
    }

    fun init() {
        Realm.init(this.ctx)
        val firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        if (user != null) {
            this.loadInitializeData()
        }
        view.registFBLogin(this)
    }

    override fun onSuccess(loginResult: LoginResult) {
        val firebaseAuth = FirebaseAuth.getInstance()
        val credential = FacebookAuthProvider.getCredential(loginResult.accessToken.token)
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { res ->
                    if (res.isSuccessful()) {
                        loadInitializeData()
                    } else {
                        Helper.showToast(ctx, ctx.getString(R.string.err_fail_login))
                    }
                }
    }

    override fun onCancel() {
    }

    override fun onError(error: FacebookException) {
    }
}