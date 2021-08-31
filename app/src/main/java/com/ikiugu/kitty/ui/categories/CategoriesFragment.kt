package com.ikiugu.kitty.ui.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikiugu.kitty.R
import com.ikiugu.kitty.databinding.FragmentCategoriesBinding
import com.ikiugu.kitty.viewModels.CatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment(R.layout.fragment_categories) {
    private val catViewModel by viewModels<CatViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCategoriesBinding.bind(view)

        val categoriesAdapter = CategoriesAdapter()

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

    }
}