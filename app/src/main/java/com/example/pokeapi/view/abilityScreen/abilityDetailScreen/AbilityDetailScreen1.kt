package com.example.pokeapi.view.abilityScreen.abilityDetailScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.pokeapi.view.abilityScreen.AbilityViewModel

@Composable
fun AbilityDetailScreen1(viewModel: AbilityViewModel, name: String) {
    val abilityDetail1 by viewModel.abilityDetail1.collectAsState()

    LaunchedEffect(name) {
        viewModel.fetchAbilityDetail1(name)
    }

    abilityDetail1?.let { abilityDetail ->
        Text(
            text = abilityDetail.name.uppercase(),
            color = Color.White,
            textAlign = TextAlign.Justify
        )
        Text(
            text = "${abilityDetail.effect_entries[1].effect} \n",
            color = Color.White,
            textAlign = TextAlign.Justify
        )
    }
}