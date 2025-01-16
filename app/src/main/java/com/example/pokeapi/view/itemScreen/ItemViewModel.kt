package com.example.pokeapi.view.itemScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.database.ItemData
import com.example.pokeapi.data.ItemRepository
import com.example.pokeapi.data.Resource
import com.example.pokeapi.model.ItemDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor
    (private val itemRepository: ItemRepository): ViewModel() {

    private val _allItem =  MutableStateFlow<Resource<List<ItemData>>>(Resource.Loading())
    val allItem: StateFlow<Resource<List<ItemData>>> = _allItem

    private val _itemDetail = MutableStateFlow<ItemDetailResponse?>(null)
    val itemDetail: StateFlow<ItemDetailResponse?> = _itemDetail

    init {
        fetchAllItem()
    }

    fun fetchAllItem(){
        viewModelScope.launch {
            itemRepository.getAllItem().collect(){result->
                _allItem.value = result
            }
        }
    }

    fun fetchItemDetail(name: String){
        viewModelScope.launch {
            _itemDetail.value = itemRepository.getItemDetail(name)
        }
    }

}