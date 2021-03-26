package com.alaksion.sneakerdex.di

import com.alaksion.sneakerdex.data.remote.SneakerDexRemoteDataSourceImpl
import com.alaksion.sneakerdex.data.repository.SneakerRepositoryImpl
import com.alaksion.sneakerdex.domain.usecase.GetSneakerUseCase
import com.alaksion.sneakerdex.domain.usecase.GetSneakersListUseCase
import com.alaksion.sneakerdex.presentation.sneakerdetail.SneakerDetailViewModel
import com.alaksion.sneakerdex.presentation.sneakerlist.SneakerListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val sneakerModule = module {

    factory { SneakerDexRemoteDataSourceImpl() }
    factory { SneakerRepositoryImpl(dataSource = get()) }
    factory { GetSneakerUseCase(repositoryImpl = get()) }
    factory { GetSneakersListUseCase(repositoryImpl = get()) }

    viewModel {
        SneakerListViewModel(application = get(), useCase = get())
    }

    viewModel {
        SneakerDetailViewModel(application = get(), useCase = get())
    }

}