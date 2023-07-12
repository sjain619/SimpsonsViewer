package com.sample.codebase_sdk.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sample.codebase_sdk.R
import com.sample.codebase_sdk.databinding.FragmentDetailBinding
import com.sample.codebase_sdk.viewModel.CharacterViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "Detail Fragment"

@AndroidEntryPoint
class DetailFragment: Fragment() {

    private val viewModel by lazy{
        ViewModelProvider(requireActivity())[CharacterViewModel::class.java]
    }

    private val binding by lazy{
        FragmentDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        viewModel.selectedChar.observe(viewLifecycleOwner){character ->
            Log.d(TAG, "$character")
            character?.let{
                binding.characterName.text = it.name
                binding.characterDesc.text = it.description

                Picasso
                    .get()
                    .load(it.imageUrl)
                    .placeholder(R.drawable.animate_loading)
                    .error(R.drawable.placeholder)
                    .into(binding.characterCoverItem)
            }

        }

        return binding.root

    }
}