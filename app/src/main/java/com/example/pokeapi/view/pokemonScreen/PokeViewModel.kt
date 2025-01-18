package com.example.pokeapi.view.pokemonScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.PokeRepository
import com.example.pokeapi.data.Resource
import com.example.pokeapi.data.database.PokeData
import com.example.pokeapi.view.modelUi.PokeDetailsModel
import com.example.pokeapi.model.FormResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeViewModel
@Inject constructor(private val pokeRepository: PokeRepository) : ViewModel() {
    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _allPokemon = MutableStateFlow<Resource<List<PokeData>>>(Resource.Loading())
    val allPokemon: StateFlow<Resource<List<PokeData>>> = _allPokemon

    private val _pokemonDetail = MutableStateFlow<PokeDetailsModel?>(null)
    val pokemonDetail: StateFlow<PokeDetailsModel?> get() = _pokemonDetail

    private val _pokemonImage = mutableStateOf<FormResponse?>(null)
    val pokemonImage: State<FormResponse?> get() = _pokemonImage

    init {
        fetchAllPokemon()
    }

    fun fetchAllPokemon() {
        viewModelScope.launch {
            pokeRepository.getAllPokemon().collect() { result ->
                _allPokemon.value = result
            }
        }
    }

    fun fetchPokemonDetail(name: String) {
        _isLoading.value = true
        viewModelScope.launch {
            delay(2000)
            try {
                val resultDetail = pokeRepository.getPokemonByName(name)?.toPokeDetailModel()
                _pokemonDetail.value = resultDetail
                val resultImage = pokeRepository.getPokemonImage(name)
                _pokemonImage.value = resultImage
                _isLoading.value = false
            } catch (e: Exception) {
                _pokemonDetail.value = null
                _isLoading.value = false
            }
        }
    }
}