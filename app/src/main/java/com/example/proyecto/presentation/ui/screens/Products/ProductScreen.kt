package com.example.proyecto.presentation.ui.screens.Products

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyecto.domain.model.Product
import com.example.proyecto.presentation.navigation.Screen
import com.example.proyecto.presentation.viewmodel.products.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavController,
                  productViewModel: ProductViewModel = koinViewModel()
) {
    var expanded by remember { mutableStateOf(false) }
    val products by productViewModel.products.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(

                title = { Text("Listado de productos") },
                actions ={
                    IconButton(onClick = { navController.navigate(Screen.Login.route)  }) {
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
                        DropdownMenuItem(text = { Text("Añadir") },
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
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
        )
        {
            items(products) { product ->
                ProductCard(product, navController)
            }


        }
    }
}

@Composable
fun ProductCard(product: Product,
                navController: NavController) {

    var isExpanded by remember { mutableStateOf(false) }
    Card(onClick = { isExpanded = !isExpanded }) {
        Row {
            Column {
                Icon(
                    imageVector = if (isExpanded) Icons.Default.Remove else Icons.Default.Add,
                    contentDescription = if (isExpanded) "colapsar" else "expandir",
                    modifier = Modifier.clickable {
                        isExpanded = !isExpanded
                    }
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = product.name)
                if (isExpanded) {
                    Text(text = product.description, modifier = Modifier.padding(1.dp))
                    Text(text = product.type, modifier = Modifier.padding(1.dp))
                    Button(onClick = {navController.navigate(Screen.Products.route)},
                        enabled = true) {
                        Text("modificar")
                    }
                }
            }
        }
    }
}
