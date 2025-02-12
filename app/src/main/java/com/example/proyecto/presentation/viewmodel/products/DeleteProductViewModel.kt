package com.example.proyecto.presentation.viewmodel.products
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.domain.usecase.products.DeleteProductsUseCase
import kotlinx.coroutines.launch

class DeleteProductViewModel(
    private val deleteProductsUseCase: DeleteProductsUseCase
) : ViewModel() {

    fun deleteProduct(id: String) {
        viewModelScope.launch {
            deleteProductsUseCase.invoke(id)
        }
    }

}