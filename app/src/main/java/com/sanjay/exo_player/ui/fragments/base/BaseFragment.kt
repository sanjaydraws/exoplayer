package com.sanjay.exo_player.ui.fragments.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.lang.Exception


abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try{
            render {
                initViews(view)
                setupListener()
                initObservers()
                loadData()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    protected fun showToast(msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
    private fun render(base:() ->Unit){
        base.invoke()
    }

    abstract fun initViews(view: View)
    abstract fun setupListener()
    abstract fun initObservers()
    abstract fun loadData()
}