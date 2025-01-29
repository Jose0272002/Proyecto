package com.example.proyecto.presentation.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyecto.R
import com.example.proyecto.presentation.navigation.Screen
import com.example.proyecto.presentation.viewmodel.login.UsernamePasswordViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginScreen(
    navController: NavController,
          usernamePasswordViewModel: UsernamePasswordViewModel = koinViewModel()
) {
    val username by usernamePasswordViewModel.username.collectAsState()
    val password by usernamePasswordViewModel.password.collectAsState()
    val botonActivo = username.isNotEmpty() && password.length>=6
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Error al iniciar sesión") },
            text = { Text("Revisa los campos") },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
    Column(modifier= Modifier
        .fillMaxSize()
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.tienda),
            contentDescription = "imagen  de  la "
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row {

            TextField(
                value = username,
                onValueChange = { newUsername -> usernamePasswordViewModel.setUsername(newUsername)},
                label = { Text("Username") },
                placeholder = { Text("Nombre de usuario")},
                modifier = Modifier.fillMaxWidth()

            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row {

            TextField(
                value = password,
                onValueChange = { newPassword -> usernamePasswordViewModel.setPassword(newPassword)},
                label = { Text("Password") },
                placeholder = { Text("Contraseña")},
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            Button(
                onClick = {
                    if (usernamePasswordViewModel.isValidLogin()){
                        navController.navigate(Screen.Products.route)
                    }
                    else{
                        showDialog =true
                    }
                    usernamePasswordViewModel.clear()
                },
                enabled = botonActivo
            )
            {
                Text("Login")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(
                onClick = {
                    navController.navigate(Screen.Products.route)
                },
                enabled = true
            )
            {
                Text("Register")
            }
        }
        
    }
}