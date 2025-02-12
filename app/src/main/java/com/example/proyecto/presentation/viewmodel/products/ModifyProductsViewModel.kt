import androidx.lifecycle.ViewModel
import com.example.proyecto.domain.model.Product
import com.example.proyecto.domain.usecase.products.AddProductsUseCase
import com.example.proyecto.domain.usecase.products.ModifyProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ModifyProductsViewModel(
    val modifyProductsUseCase: ModifyProductsUseCase
) : ViewModel() {
    private val _product = MutableStateFlow(
        Product()
    )
    val product: StateFlow<Product> = _product

    fun setName(name: String) {
        _product.value = _product.value.copy(
            name = name
        )
    }

    fun setType(type: String) {
        _product.value = _product.value.copy(
            type = type
        )
    }

    fun setValue(value: Double) {
        _product.value = _product.value.copy(
            value = value
        )
    }

    fun setDescription(description: String) {
        _product.value = _product.value.copy(
            description = description
        )
    }
}