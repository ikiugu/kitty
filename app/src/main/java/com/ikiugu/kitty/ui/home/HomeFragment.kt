package com.ikiugu.kitty.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ikiugu.kitty.R
import com.ikiugu.kitty.databinding.FragmentHomeBinding
import com.ikiugu.kitty.viewModels.CatViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val catViewModel by viewModels<CatViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = catViewModel

        return binding.root
    }
}