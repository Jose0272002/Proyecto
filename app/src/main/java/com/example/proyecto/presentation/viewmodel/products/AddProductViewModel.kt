package com.example.proyecto.presentation.viewmodel.products

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.example.proyecto.domain.model.Product
import com.example.proyecto.domain.usecase.products.AddProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.factory.KoinViewModelFactory
import org.koin.core.Koin

class AddProductViewModel(
    val addProductUseCase: AddProductsUseCase
) : ViewModel() {
    private val _product = MutableStateFlow(
        Product(0, "", "", "", 0)
    )
    val product: StateFlow<Product> = _product

    fun setId(id: Int) {
        _product.value = _product.value.copy(
            id = id
        )
    }

    fun setName(name: String) {
        _product.value = _product.value.copy(
            name = name
        )
    }

    fun setDescription(description: String) {
        _product.value = _product.value.copy(
            description = description
        )
    }

    fun save() {
        viewModelScope.launch {
            addProductUseCase(product.value)
        }
    }
}