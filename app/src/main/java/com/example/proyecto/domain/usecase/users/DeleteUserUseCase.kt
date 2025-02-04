package com.example.proyecto.domain.usecase.users

import com.example.proyecto.di.UserRepository

class DeleteUserUseCase(private val userRepository: UserRepository) {
    // Implementamos la lógica del caso de uso dentro de este método
    suspend operator fun invoke(id: String): Boolean {
        // Lógica del caso de uso
        return userRepository.deleteUser(id)
    }
}