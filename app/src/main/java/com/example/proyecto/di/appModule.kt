package com.example.proyecto.di

import UsersScreenViewModel
import com.example.proyecto.data.source.remote.ProductFirestoreRepository
import com.example.proyecto.domain.usecase.products.AddProductsUseCase
import com.example.proyecto.domain.usecase.products.DeleteProductsUseCase
import com.example.proyecto.domain.usecase.products.ListProductsUseCase
import com.example.proyecto.domain.usecase.users.DeleteUserUseCase
import com.example.proyecto.domain.usecase.users.GetUsersUseCase
import com.example.proyecto.presentation.viewmodel.login.UsernamePasswordViewModel
import com.example.proyecto.presentation.viewmodel.products.AddProductViewModel
import com.example.proyecto.presentation.viewmodel.products.DeleteProductViewModel
import com.example.proyecto.presentation.viewmodel.products.ProductViewModel
//import com.example.proyecto.presentation.viewmodel.products.ProductsScreenViewModel
//import com.example.proyecto.presentation.viewmodel.users.UsersScreenViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // FIRESTORE
    single { FirebaseFirestore.getInstance() }

    // REPOSITIRIES
    // Singleton del respositorio de usuarios, se le inyecta el FirebaseFirestore creado en la sección anterior
    single { UserRepository(get()) }

    single { ProductFirestoreRepository(get()) }

    // USE CASES
    // Usamos factory para que proporcione una instancia del UseCase cada vez que se solicite
    factory { GetUsersUseCase(get()) }
    // Usamos factory para que proporcione una instancia del UseCase cada vez que se solicite
    factory { DeleteUserUseCase(get()) }
    factory { AddProductsUseCase(get()) }
    factory { ListProductsUseCase(get()) }
    factory { DeleteProductsUseCase(get()) }

    // VIEW MODELS
    // Crea el viewModel con las dependencias que tenga definidas

    // viewModel { ProductsScreenViewModel(get(), get()) }
    viewModel { ProductViewModel(get(), get()) }
    viewModel { DeleteProductViewModel(get()) }
    viewModel { UsernamePasswordViewModel() }
    viewModel { UsersScreenViewModel(get(), get()) }
    viewModel { AddProductViewModel(get()) }
}