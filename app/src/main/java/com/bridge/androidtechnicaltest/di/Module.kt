package com.bridge.androidtechnicaltest.di

import com.bridge.androidtechnicaltest.network.PupilRepository
import com.bridge.androidtechnicaltest.network.PupilRepositoryImpl
import com.bridge.androidtechnicaltest.ui.PupilViewModel
import org.koin.android.viewmodel.dsl.viewModel

import org.koin.dsl.module

val networkModule = module {
    factory { PupilAPIFactory.retrofitPupil() }
}

val databaseModule = module {
    factory { DatabaseFactory.getDBInstance(get()) }
//    single<IPupilRepository>{ PupilRepository(get(), get()) }
}

val repositoryModule = module {
        single<PupilRepository>{ PupilRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel {
        PupilViewModel(get())
    }
}