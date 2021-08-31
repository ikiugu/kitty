package com.ikiugu.kitty.ui.breeds.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikiugu.kitty.R
import com.ikiugu.kitty.databinding.FragmentBreedsBinding
import com.ikiugu.kitty.models.CatBreed
import com.ikiugu.kitty.viewModels.CatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class BreedsFragment : Fragment(R.layout.fragment_breeds), BreedListingAdapter.OnItemClickListener {
    private val catViewModel by viewModels<CatViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentBreedsBinding.bind(view)

        val breedListingAdapter = BreedListingAdapter(this)

        binding.apply {
            breedsRecyclerView.apply {
                adapter = breedListingAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        catViewModel.getCatBreeds()

        catViewModel.catBreeds.observe(viewLifecycleOwner, { catBreeds ->
            breedListingAdapter.submitList(catBreeds)
            breedListingAdapter.notifyDataSetChanged()
        })


        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            catViewModel.catsEvent.collect { event ->
                when (event) {
                    is CatViewModel.CatsEvent.NavigateToDisplayBreedsScreen -> {
                        val catBreed = event.breed
                        findNavController().navigate(
                            BreedsFragmentDirections.actionNavBreedsToNavBreedDisplay(
                                catBreed
                            )
                        )
                    }
                }
            }
        }

    }

    override fun onItemClick(breed: CatBreed) {
        catViewModel.setCatBreed(breed)
    }

}