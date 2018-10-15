package com.linkbit.android.presentation.view.friend.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.linkbit.android.R
import com.linkbit.android.entity.UserModel
import com.linkbit.android.presentation.EndlessRecyclerViewScrollListener
import com.linkbit.android.presentation.adapter.friend.FriendListAdapter
import com.linkbit.android.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_friend_search.*
import kotlinx.android.synthetic.main.fragment_any_list.*

class FriendSearchActivity : BaseActivity<FriendSearchPresenter>(), FriendSearchView {
    override val presenter: FriendSearchPresenter = FriendSearchPresenter(this)
    private lateinit var friendListAdapter: FriendListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_search)
        this.friendListAdapter = FriendListAdapter(this, null)
        val layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerview_any.layoutManager = layoutManager
        recyclerview_any.adapter = friendListAdapter
        recyclerview_any.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                val text = et_friend_search_text.text.toString()
                presenter.searchFriend(text, page)
            }
        })
    }

    override fun addFriendItems(items: List<UserModel>) {
        friendListAdapter.addItems(items)
        friendListAdapter.notifyDataSetChanged()
    }
}
