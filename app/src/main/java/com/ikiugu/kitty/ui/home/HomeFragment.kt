package com.ikiugu.kitty.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ikiugu.kitty.databinding.FragmentHomeBinding
import com.ikiugu.kitty.viewModels.CatViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val catViewModel by viewModels<CatViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        catViewModel.getImagesByCategories("1")

        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}