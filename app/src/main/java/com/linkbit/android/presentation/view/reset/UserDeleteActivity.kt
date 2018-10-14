package com.linkbit.android.presentation.view.reset

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import com.linkbit.android.R
import com.linkbit.android.helper.ProgressDialogHelper
import com.linkbit.android.presentation.base.BaseActivity
import com.linkbit.android.presentation.view.splash.SplashActivity
import kotlinx.android.synthetic.main.activity_user_delete.*

class UserDeleteActivity : BaseActivity<UserDeletePresenter>(), UserDeleteView {

    override val presenter: UserDeletePresenter = UserDeletePresenter(this)
    private var progressBar: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_delete)
        btn_user_delete.setOnClickListener { this.presenter.doReset() }
    }

    override fun showProgress(status: Boolean) {
        if(status){
            this.progressBar = ProgressDialogHelper.show(this, "계정 삭제중", false)
        }else{
            this.progressBar!!.dismiss()
        }
    }

    override fun finish(){
        startActivity(Intent(this, SplashActivity::class.java))
        super.finish()
    }
}
