package com.example.pokeapi.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokeapi.model.PokeModel
import java.io.IOException
import javax.inject.Inject

class PokePagingSource @Inject constructor(private val api: PokeApiService) :
    PagingSource<Int, PokeModel>() {

    override fun getRefreshKey(state: PagingState<Int, PokeModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokeModel> {

        return try {
            val page = params.key ?: 0
            val response = api.getPokemon(page)
            val pokemons = response.results

            val prevKey = if (page > 0) page - 20 else null
            val nextKey = if (response.next != null) page + 20 else null

            LoadResult.Page(
                data = pokemons.map { it.toPresentation() },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }

}