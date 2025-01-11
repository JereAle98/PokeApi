package com.example.pokeapi.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.pokeapi.data.PokeRepository
import com.example.pokeapi.data.response.PokeDetailResponse
import com.example.pokeapi.model.DetailsModel
import com.example.pokeapi.model.PokeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor
    (private val pokeRepository: PokeRepository): ViewModel(){

    private val _pokemon = mutableStateOf<PokeDetailResponse?>(null)
    val pokemon: State<PokeDetailResponse?> get() = _pokemon

    fun fetchPokemon(name: String) {
        viewModelScope.launch {
            try {
                val result = pokeRepository.getPokemonByName(name)
                _pokemon.value = result
            } catch (e: Exception) {
                _pokemon.value = null
            }
        }
    }

    val pokemons: Flow<PagingData<PokeModel>> =
        pokeRepository.getAllPokemons()

}