package com.example.proyecto.presentation.navigation

sealed class Screen(val route:String){
    object Login: Screen("login")
    object Products: Screen("products")
    object AddProducts :Screen("addProducts")
}