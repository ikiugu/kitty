package com.ikiugu.kitty.ui.breeds.display

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ikiugu.kitty.R
import com.ikiugu.kitty.databinding.FragmentBreedDisplayBinding


class BreedDisplayFragment : Fragment(R.layout.fragment_breed_display) {
    private val args: BreedDisplayFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentBreedDisplayBinding.bind(view)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.catBreed.name

        binding.apply {
            Glide.with(breedImage)
                .load(args.catBreed.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(R.drawable.ic_error)
                .centerCrop()
                .into(breedImage)
            breedDescription.text = args.catBreed.description
            breedTemperament.text = "Temperament: ${args.catBreed.temperament}"
            breedOrigin.text = "From: ${args.catBreed.origin}"
            breedWiki.text = args.catBreed.wikipediaURL
        }

    }
}