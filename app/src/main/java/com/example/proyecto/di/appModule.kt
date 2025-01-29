package com.example.proyecto.di

import com.example.proyecto.data.source.remote.ProductFirestoreRepository
import com.example.proyecto.data.source.remote.UserRepository
import com.example.proyecto.domain.usecase.products.AddProductsUseCase
import com.example.proyecto.domain.usecase.products.DeleteProductsUseCase
import com.example.proyecto.domain.usecase.products.ListProductsUseCase
import com.example.proyecto.domain.usecase.users.DeleteUserUseCase
import com.example.proyecto.domain.usecase.users.GetUsersUseCase
import com.example.proyecto.presentation.viewmodel.products.AddProductViewModel
import com.example.proyecto.presentation.viewmodel.products.DeleteProductViewModel
import com.example.proyecto.presentation.viewmodel.products.ProductViewModel
//import com.example.proyecto.presentation.viewmodel.products.ProductsScreenViewModel
//import com.example.proyecto.presentation.viewmodel.users.UsersScreenViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AddProductsUseCase(get()) }
    single { DeleteProductsUseCase(get()) }
    single { ListProductsUseCase(get()) }
    viewModel { AddProductViewModel(get()) }
    viewModel { DeleteProductViewModel(get()) }
    viewModel { ProductViewModel(get()) }
}