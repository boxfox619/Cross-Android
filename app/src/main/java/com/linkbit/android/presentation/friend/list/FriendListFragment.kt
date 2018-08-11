package com.linkbit.android.presentation.friend.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.linkbit.android.R
import com.linkbit.android.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_friend_list.*

class FriendListFragment : BaseFragment<FriendListPresenter>(), FriendListView {
    override val presenter: FriendListPresenter = FriendListPresenter(this)
    lateinit var friendListAdapter: FriendListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_friend_list, container, false)
        this.friendListAdapter = FriendListAdapter(this.context)
        recyclerview_friend.adapter = friendListAdapter
        return view
    }

    override fun onDetach() {
        super.onDetach()
        presenter.destory()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                FriendListFragment()
    }
}
