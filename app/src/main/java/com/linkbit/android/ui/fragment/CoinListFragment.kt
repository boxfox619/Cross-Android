package com.linkbit.android.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linkbit.android.R
import com.linkbit.android.adapter.CoinListViewAdapter
import com.linkbit.android.model.Coin

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [CoinListFragment.OnListFragmentInteractionListener] interface.
 */
class CoinListFragment : Fragment() {

    private lateinit var listener: (item: Coin)->Unit
    private var items = ArrayList<Coin>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_coin_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                adapter = CoinListViewAdapter(items, listener)
            }
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(listener: (item: Coin)-> Unit) = CoinListFragment().apply{
            this.listener = listener
        }
    }
}
