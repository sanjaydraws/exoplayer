package com.sanjay.exo_player.adapters

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewScrollListener : RecyclerView.OnScrollListener() {
    private var firstVisibleItem = 0
    private var visibleItemCount = 0

    companion object{
        const val TAG = "RecyclerScrollListener"
    }

    @Volatile
    private var mEnabled = true
    private var mPreLoadCount = 0
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (mEnabled) {
            val manager = recyclerView.layoutManager
            require(manager is LinearLayoutManager) { "Expected recyclerview to have linear layout manager" }
            val mLayoutManager = manager
            visibleItemCount = mLayoutManager.childCount
            Log.d(TAG, "onScrolled: $visibleItemCount")

            firstVisibleItem = mLayoutManager.findFirstCompletelyVisibleItemPosition()
            onItemIsFirstVisibleItem(firstVisibleItem)
        }
    }

    /**
     * Called when end of scroll is reached.
     *
     * @param recyclerView - related recycler view.
     */
    abstract fun onItemIsFirstVisibleItem(index: Int)
    fun disableScrollListener() {
        mEnabled = false
    }

    fun enableScrollListener() {
        mEnabled = true
    }

    fun setPreLoadCount(mPreLoadCount: Int) {
        this.mPreLoadCount = mPreLoadCount
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        when(newState){
            RecyclerView.SCROLL_STATE_IDLE ->{
                Log.d(TAG, "onScrollStateChanged: SCROLL_STATE_IDLE")
            }
            RecyclerView.SCROLL_STATE_DRAGGING ->{
                Log.d(TAG, "onScrollStateChanged: SCROLL_STATE_DRAGGING")
            }
            RecyclerView.SCROLL_STATE_SETTLING ->{
                Log.d(TAG, "onScrollStateChanged: SCROLL_STATE_SETTLING")

            }
        }
    }
}