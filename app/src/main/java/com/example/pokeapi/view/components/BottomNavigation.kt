package com.example.pokeapi.view.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pokeapi.navigation.Routes
import com.example.pokeapi.ui.theme.PixelifySans
import com.example.pokeapi.ui.theme.principal
import com.example.pokeapi.ui.theme.secundario
import com.example.pokeapi.ui.theme.tertario

@Composable
fun BottomNav(navHostController: NavHostController, routes: List<Routes>){
    BottomAppBar() {
        NavigationBar (
            containerColor = tertario
        ){
            val currentRoute = CurrentRoute(navHostController)
            routes.forEach{ item->
                NavigationBarItem(selected = currentRoute == item.route,
                    onClick = {navHostController.navigate(item.route)},
                    icon = {
                        Icon(painter = painterResource(id = item.icon), contentDescription = item.title, tint = secundario)
                    },
                    label = {
                        Text(text = item.title, fontFamily = PixelifySans)
                    },
                    alwaysShowLabel = true
                    )

            }
        }
    }
}

@Composable
fun CurrentRoute(navHostController: NavHostController): String?{
    val current by navHostController.currentBackStackEntryAsState()
    return current?.destination?.route
}