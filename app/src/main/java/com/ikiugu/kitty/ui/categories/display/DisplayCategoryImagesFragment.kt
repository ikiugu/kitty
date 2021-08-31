package com.ikiugu.kitty.ui.categories.display

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.ikiugu.kitty.R
import com.ikiugu.kitty.databinding.FragmentDisplayCategoryImagesBinding
import com.ikiugu.kitty.viewModels.CatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayCategoryImagesFragment : Fragment(R.layout.fragment_display_category_images) {

    private val catViewModel by viewModels<CatViewModel>()

    private val args: DisplayCategoryImagesFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDisplayCategoryImagesBinding.bind(view)

        val imagesAdapter = ImagesAdapter()

        binding.apply {
            imagesRecyclerView.apply {
                adapter = imagesAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
                setHasFixedSize(true)
            }
        }

        imagesAdapter.submitList(args.categoryResults.toList())
    }


}