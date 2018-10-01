package com.linkbit.android.presentation.view.wallet.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.linkbit.android.R
import com.linkbit.android.entity.UserModel
import com.linkbit.android.entity.WalletModel
import com.linkbit.android.presentation.base.BaseFragment
import com.linkbit.android.presentation.view.friend.list.FriendListAdapter
import com.linkbit.android.presentation.SimpleTextChangeListener
import kotlinx.android.synthetic.main.fragment_withdraw_step2.view.*

class WithdrawTargetSelectStepFragment : BaseFragment<WithdrawTargetSelectStepPresenter>(), WithdrawTargetSelectStepView {
    private lateinit var friendListAdapter: FriendListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_withdraw_step2, container, false)
        friendListAdapter = FriendListAdapter(context) { address -> presenter.setTargetAddress(address) }
        view.recyclerview_withdraw_friend_list.adapter = friendListAdapter
        view.tabhost_withdraw_step2.setOnTabChangedListener { presenter.tabChanged() }
        view.et_withdraw_step2_address.addTextChangedListener(SimpleTextChangeListener { presenter.setTargetAddress(it) })
        this.presenter.init()
        return view

    }

    override fun setFriendItems(friendList: List<UserModel>) {
        friendListAdapter.let {
            it.clear()
            it.addItems(friendList)
            it.notifyDataSetChanged()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(onSelect: (wallet: WalletModel?) -> Unit) =
                WithdrawTargetSelectStepFragment().apply {
                    this.presenter = WithdrawTargetSelectStepPresenter(this, onSelect)
                }
    }
}
