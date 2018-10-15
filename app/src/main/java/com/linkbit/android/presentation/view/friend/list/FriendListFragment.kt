package com.linkbit.android.presentation.view.friend.list

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.linkbit.android.R
import com.linkbit.android.entity.UserModel
import com.linkbit.android.presentation.adapter.friend.FriendListAdapter
import com.linkbit.android.presentation.base.BaseFragment

class FriendListFragment : BaseFragment<FriendListPresenter>(), FriendListView {

    private lateinit var friendListAdapter: FriendListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_any_list, container, false)
        this.friendListAdapter = FriendListAdapter(this.context, null)
        view.findViewById<RecyclerView>(R.id.recyclerview_any).adapter = friendListAdapter
        this.presenter = FriendListPresenter(this)
        this.presenter.loadFriendList()
        return view
    }

    override fun onDetach() {
        super.onDetach()
        presenter.destory()
    }

    override fun setFriendItems(items: List<UserModel>) {
        friendListAdapter.clear()
        friendListAdapter.addItems(items)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                FriendListFragment()
    }
}
