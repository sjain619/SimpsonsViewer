package com.sample.codebase_sdk.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.codebase_sdk.adapter.CharacterAdapter
import com.sample.codebase_sdk.databinding.FragmentCharacterBinding
import com.sample.codebase_sdk.model.CharacterProfile
import com.sample.codebase_sdk.util.AppType
import com.sample.codebase_sdk.util.UIState
import com.sample.codebase_sdk.viewModel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class CharacterFragment: Fragment() {
    private val binding by lazy{
        FragmentCharacterBinding.inflate(layoutInflater)
    }

    private val characterViewModel by lazy {
        ViewModelProvider(requireActivity())[CharacterViewModel::class.java]
    }

     val characterAdapter: CharacterAdapter by lazy {
        CharacterAdapter{
            Log.d(TAG, "$it: ")
            characterViewModel.setSelectedItem(it)
            binding.slidingPaneLayout.openPane()
        }
    }

    private lateinit var  charType: AppType
    val stringSearch: MutableList<CharacterProfile> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        charType = requireActivity().intent.getSerializableExtra("APP_TYPE") as AppType
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.mainFragment.searchChar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
            val filteredList = stringSearch.filter { chars ->
                newText?.let {
                    chars.name.uppercase().contains(it.uppercase())
                } ?: false
            }
                characterAdapter.updateChar(filteredList)
                return true
            }
        })

        binding.mainFragment.charsRv.apply{
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = characterAdapter
        }

        characterViewModel.characters.observe(viewLifecycleOwner){
            when(it) {
                is UIState.Loading -> {}
                is UIState.Success -> {
                    characterAdapter.updateChar(it.response)
                    stringSearch.clear()
                    stringSearch.addAll(it.response)
                }
                is UIState.Failure -> {}
                else -> {}
            }
        }

        characterViewModel.getCharacterList(charType)

        val onBackPressedCallback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                view?.let {
                    binding.slidingPaneLayout.closePane() }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,onBackPressedCallback)

        return binding.root

    }

}

