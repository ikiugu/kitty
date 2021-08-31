package com.ikiugu.kitty.ui.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ikiugu.kitty.R
import com.ikiugu.kitty.databinding.FragmentFavouritesBinding
import com.ikiugu.kitty.viewModels.CatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    private val catViewModel by viewModels<CatViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFavouritesBinding.bind(view)

        val favoritesAdapter = FavoritesAdapter()

        binding.apply {
            favouritesRecyclerView.apply {
                adapter = favoritesAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
                setHasFixedSize(true)
            }
        }

        catViewModel.getFavoriteImages()

        catViewModel.favoriteImages.observe(viewLifecycleOwner, { favorites ->
            if (favorites.isEmpty()) {
                Snackbar.make(requireView(), "You have no favorite images", Snackbar.LENGTH_LONG)
                    .show()
                binding.emptyListLayout.visibility = View.VISIBLE
                binding.favouritesRecyclerView.visibility = View.GONE
            } else {
                binding.emptyListLayout.visibility = View.GONE
                binding.favouritesRecyclerView.visibility = View.VISIBLE
                favoritesAdapter.submitList(favorites)
                favoritesAdapter.notifyDataSetChanged()
            }
        })
    }

}