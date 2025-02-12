package com.example.proyecto.presentation.ui.screens.Products

import ModifyProductsViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.proyecto.presentation.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyProductScreen(navController: NavController,
                        modifyProductsViewModel: ModifyProductsViewModel = koinViewModel()
) {
    val product by modifyProductsViewModel.product.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(

                title = { Text("Añadir producto") },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Products.route) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Volver"
                        )
                    }
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Menu",

                            )
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        DropdownMenuItem(text = { Text("Listado") },
                            onClick = { navController.navigate(Screen.Products.route) })
                        HorizontalDivider()
                        DropdownMenuItem(text = { Text("Modificar") },
                            onClick = { navController.navigate(Screen.AddProducts.route) })

                        HorizontalDivider()
                        DropdownMenuItem(text = { Text("Login") },
                            onClick = { navController.navigate(Screen.Login.route) })
                    }

                }
            )
        }

    ) { paddingValues ->
        Column (modifier = Modifier.padding(paddingValues)){ {

        }
        }
    }
}