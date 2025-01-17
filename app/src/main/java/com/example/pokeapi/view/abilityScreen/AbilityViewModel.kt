package com.example.pokeapi.view.abilityScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.AbilityRepository
import com.example.pokeapi.data.ItemRepository
import com.example.pokeapi.data.Resource
import com.example.pokeapi.data.database.AbilityData
import com.example.pokeapi.data.database.ItemData
import com.example.pokeapi.model.AbilityDetailResponse
import com.example.pokeapi.model.ItemDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AbilityViewModel @Inject constructor
    (private val abilityRepository: AbilityRepository): ViewModel(){

    private val _allAbility =  MutableStateFlow<Resource<List<AbilityData>>>(Resource.Loading())
    val allAbility: StateFlow<Resource<List<AbilityData>>> = _allAbility

    private val _abilityDetail1 = MutableStateFlow<AbilityDetailResponse?>(null)
    val abilityDetail1: StateFlow<AbilityDetailResponse?> = _abilityDetail1

    private val _abilityDetail2 = MutableStateFlow<AbilityDetailResponse?>(null)
    val abilityDetail2: StateFlow<AbilityDetailResponse?> = _abilityDetail2

    init {
        fetchAllAbility()
    }

    fun fetchAllAbility(){
        viewModelScope.launch {
            abilityRepository.getAllItem().collect(){result->
                _allAbility.value = result
            }
        }
    }

    fun fetchAbilityDetail1(name: String){
        viewModelScope.launch {
            _abilityDetail1.value = abilityRepository.getAbilityDetail(name)
        }
    }
    fun fetchAbilityDetail2(name: String){
        viewModelScope.launch {
            _abilityDetail2.value = abilityRepository.getAbilityDetail(name)
        }
    }
}