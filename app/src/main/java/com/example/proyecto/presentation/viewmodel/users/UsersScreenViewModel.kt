import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.domain.model.User
import com.example.proyecto.domain.usecase.users.DeleteUserUseCase
import com.example.proyecto.domain.usecase.users.GetUsersUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UsersScreenViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    // La lista de usuarios se carga usando el getUsersUseCase
    private var _users = getUsersUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val users: StateFlow<List<User>> = _users

    fun removeProduct(id: String) {
        viewModelScope.launch {
            // Invoca el deleteUserUseCase
            deleteUserUseCase(id)
        }
    }
}