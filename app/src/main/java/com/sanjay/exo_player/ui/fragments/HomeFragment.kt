package com.sanjay.exo_player.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.sanjay.exo_player.R
import com.sanjay.exo_player.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var binding:FragmentHomeBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // go to faceHomePage
        binding?.facebookButton?.setOnClickListener {
            findNavController()?.navigate(R.id.action_homeFragment_to_facebookPlayerFragment)
        }

        // go to tiktok home page
        binding?.tiktokButton?.setOnClickListener {
            findNavController()?.navigate(R.id.action_homeFragment_to_tikTokPlayerFragment)
        }

        // go to instagram home page
        binding?.instagramButton?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_instagramScreenFragment)
        }

    }
}