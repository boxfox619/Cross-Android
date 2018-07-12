package com.linkbit.android.ui.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.linkbit.android.R
import com.linkbit.android.presenter.HeaderPresenter
import com.linkbit.android.ui.view.header.HeaderView
import kotlinx.android.synthetic.main.layout_header.*

class MainActivity : AppCompatActivity(), HeaderView {
    lateinit var headerPresenter: HeaderPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        headerPresenter = HeaderPresenter()
        headerPresenter.addView(this)
    }

    override fun getContext(): Context {
        return this
    }

    override fun getRecyclerView(): RecyclerView {
        return recyclerview_coin_statistics
    }
}
