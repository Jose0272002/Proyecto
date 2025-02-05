package com.example.proyecto.presentation.viewmodel.products

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyecto.data.source.remote.ProductFirestoreRepository
import com.example.proyecto.domain.model.Product
import com.example.proyecto.domain.usecase.products.ListProductsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListProductsViewModel(val listProductsUseCase: ListProductsUseCase) : ViewModel() {

    private val _products = listProductsUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val products: StateFlow<List<Product>> = _products
    

}