package com.example.pokeapi.view.pokemonScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.database.PokeData
import com.example.pokeapi.model.FormResponse
import com.example.pokeapi.data.PokeRepository
import com.example.pokeapi.model.DetailsModel
import com.example.pokeapi.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor
    (private val pokeRepository: PokeRepository): ViewModel(){

    private val _allPokemon =  MutableStateFlow<Resource<List<PokeData>>>(Resource.Loading())
    val allPokemon: StateFlow<Resource<List<PokeData>>> = _allPokemon

    private val _pokemonDetail = mutableStateOf<DetailsModel?>(null)
    val pokemonDetail: State<DetailsModel?> get() = _pokemonDetail

    private val _pokemonImage = mutableStateOf<FormResponse?>(null)
    val pokemonImage: State<FormResponse?> get() = _pokemonImage

    init {
        fetchAllPokemon()
    }

    fun fetchAllPokemon(){
        viewModelScope.launch {
            pokeRepository.getAllPokemon().collect(){result->
                _allPokemon.value = result
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

    fun fetchPokemonImage(name: String){
        viewModelScope.launch {
            try {
                val result = pokeRepository.getPokemonImage(name)
                _pokemonImage.value = result
            }catch (e: Exception){
                _pokemonDetail.value = null
            }
        }
    }




}