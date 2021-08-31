package com.ikiugu.kitty.ui.categories.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikiugu.kitty.R
import com.ikiugu.kitty.databinding.FragmentCategoriesBinding
import com.ikiugu.kitty.models.Category
import com.ikiugu.kitty.viewModels.CatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class CategoriesFragment : Fragment(R.layout.fragment_categories),
    CategoriesAdapter.OnItemClickListener {
    private val catViewModel by viewModels<CatViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCategoriesBinding.bind(view)

        val categoriesAdapter = CategoriesAdapter(this)

        binding.apply {
            categoriesRecyclerView.apply {
                adapter = categoriesAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        catViewModel.getCategories()

        catViewModel.categories.observe(viewLifecycleOwner, { categories ->
            categoriesAdapter.submitList(categories)
            categoriesAdapter.notifyDataSetChanged()
        })


        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            catViewModel.catsEvent.collect { event ->
                when (event) {
                    is CatViewModel.CatsEvent.NavigateToDisplayCatsScreen -> {
                        val images = event.images
                        Timber.i("${images.size} images received from the channel")
                        findNavController().navigate(
                            CategoriesFragmentDirections.actionNavCategoriesToNavCategoriesResult(
                                images
                            )
                        )
                    }
                }
            }
        }

    }

    override fun onItemClick(category: Category) {
        catViewModel.getImagesByCategories(category.id.toString())
    }
}