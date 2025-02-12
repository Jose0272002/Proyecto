package com.example.proyecto.presentation.ui.screens.Products

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyecto.domain.model.Product
import com.example.proyecto.presentation.navigation.Screen
import com.example.proyecto.presentation.viewmodel.products.DeleteProductViewModel
import com.example.proyecto.presentation.viewmodel.products.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    navController: NavController,
    productViewModel: ProductViewModel = koinViewModel()
) {
    var expanded by remember { mutableStateOf(false) }
    val products by productViewModel.products.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(

                title = { Text("Listado de productos") },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Login.route) }) {
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
                            onClick = { navController.navigate(Screen.AddProducts.route) })

                        HorizontalDivider()
                        DropdownMenuItem(text = { Text("Login") },
                            onClick = { navController.navigate(Screen.Login.route) })
                    }

                }
            )
        },
        floatingActionButton = {
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
fun ProductCard(
    product: Product,
    navController: NavController,
    deleteProductViewModel: DeleteProductViewModel = koinViewModel()
) {


    var isExpanded by remember { mutableStateOf(false) }
    Card(
        onClick = { isExpanded = !isExpanded }, modifier =
        if (isExpanded) {
            Modifier
                .fillMaxWidth()
                .padding(3.dp)
        } else {
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(3.dp)
        }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxHeight()) {
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = product.name)
                if (isExpanded) {
                    Column {
                        Row {
                            Text(text = product.description, modifier = Modifier.padding(1.dp))
                            Spacer(modifier = Modifier.padding(10.dp))
                        }
                        Row {
                            Text(text = product.type, modifier = Modifier.padding(1.dp))
                            Spacer(modifier = Modifier.padding(10.dp))
                        }
                        Row {
                            Text(
                                text = product.value.toString() + "€",
                                modifier = Modifier.padding(1.dp)
                            )
                        }
                        Row {
                            Button(
                                onClick = { navController.navigate(Screen.ModifyProducts.route + "/${product.id}") },
                                enabled = true,
                                colors = ButtonDefaults.buttonColors(Color.hsl(206f, 0.5f, 0.5f))

                            ) {
                                Text("modificar")
                            }
                            Button(
                                onClick = {deleteProductViewModel.deleteProduct(product.id) },
                                enabled = true,
                                colors = ButtonDefaults.buttonColors(Color.hsl(000f, 0.6f, 0.5f))
                            ) {
                                Text("Eliminar")
                            }
                        }


                    }
                }
            }
        }
    }
}
