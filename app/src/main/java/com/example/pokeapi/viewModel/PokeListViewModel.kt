package com.example.pokeapi.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.PokeRepository
import com.example.pokeapi.model.DetailsModel
import com.example.pokeapi.model.PokeWrapperModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor
    (private val pokeRepository: PokeRepository): ViewModel(){

    private val _pokemonDetail = mutableStateOf<DetailsModel?>(null)
    val pokemonDetail: State<DetailsModel?> get() = _pokemonDetail

    private val _allPokemon =  mutableStateOf<PokeWrapperModel?>(null)
    val allPokemon: State<PokeWrapperModel?> = _allPokemon

    fun fetchAllPokemon(){
        viewModelScope.launch {
            try {
                val result = pokeRepository.getAllPokemons()
                _allPokemon.value = result?.toPokeWrapperModel()
            } catch (e: Exception) {
                _allPokemon.value = null
            }
        }
    }

    fun fetchPokemonDetail(name: String) {
        viewModelScope.launch {
            try {
                val result = pokeRepository.getPokemonByName(name)?.toDetailsModel()
                _pokemonDetail.value = result
            } catch (e: Exception) {
                _pokemonDetail.value = null
            }
        }
    }




}