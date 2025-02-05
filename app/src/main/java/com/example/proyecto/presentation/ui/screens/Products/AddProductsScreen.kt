package com.example.proyecto.presentation.ui.screens.Products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyecto.presentation.navigation.Screen
import com.example.proyecto.presentation.viewmodel.products.AddProductViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController,
                     addProductsViewModel: AddProductViewModel = koinViewModel()
) {
    val product by addProductsViewModel.product.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(

                title = { Text("Añadir producto") },
                actions ={
                    IconButton(onClick = { navController.navigate(Screen.Products.route)  }) {
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
                            onClick = {navController.navigate(Screen.AddProducts.route)})

                        HorizontalDivider()
                        DropdownMenuItem(text = { Text("Login") },
                            onClick = { navController.navigate(Screen.Login.route) })
                    }

                }
            )
        },
        floatingActionButton ={
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddProducts.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir"
                )

            }
        }
    ) { paddingValues ->

        Column(Modifier.padding(paddingValues)) {
            Row {
                TextField(
                    value = product.name,
                    onValueChange = { addProductsViewModel.setName(it) },
                    label = { Text("Artículo") },
                    placeholder = { Text("Nombre del artículo") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row {
                TextField(
                    value = product.description,
                    onValueChange = { addProductsViewModel.setDescription(it) },
                    label = { Text("Descripción") },
                    placeholder = { Text("Descripción del artículo") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row {
                Button(onClick = {
                    addProductsViewModel.save()
                }) {
                    Text( "Añadir Producto")
                }
            }

        }
    }
}