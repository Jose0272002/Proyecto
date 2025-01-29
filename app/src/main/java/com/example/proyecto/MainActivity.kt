package com.example.proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto.domain.model.Product
import com.example.proyecto.presentation.navigation.NavGraph
import com.example.proyecto.presentation.navigation.Screen
import com.example.proyecto.presentation.ui.screens.login.LoginScreen
import com.example.proyecto.ui.theme.ProyectoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoTheme {
                NavGraph()
                val products = listOf(
                    Product(1,"martillo","herramienta", "martillo de mango rojo",23),
                    Product(2, "Tuerca", "material", "Tamaño pequeño", 2),
                    Product(3, "Destornillador estrella", "herramienta", "Herramienta manual",4),
                    Product(4, "Martillo", "herramienta", "Martillo de plástico",3),
                    Product(5, "Desatascador", "herramienta", "Tamaño grande",3),
                    Product(6, "Destornillador plano", "herramienta", "Herramienta manual",4)
                )
            }
        }
    }
}
