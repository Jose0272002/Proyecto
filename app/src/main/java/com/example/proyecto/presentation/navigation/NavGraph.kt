package com.example.proyecto.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto.presentation.ui.screens.Products.AddProductScreen
import com.example.proyecto.presentation.ui.screens.Products.ModifyProductScreen
import com.example.proyecto.presentation.ui.screens.Products.ProductScreen
import com.example.proyecto.presentation.ui.screens.login.LoginScreen

@Composable
fun NavGraph(startDestination: String = Screen.Login.route){
    var navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination ){
        composable(Screen.Login.route){
            LoginScreen(navController)
        }
        composable(Screen.Products.route){
            ProductScreen(navController)
        }
        composable(Screen.AddProducts.route) {
            AddProductScreen(navController)
        }
        composable(Screen.ModifyProducts.route) {
            ModifyProductScreen(navController)
        }
    }

}