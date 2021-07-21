package com.geekbrains.tests.di

import com.geekbrains.tests.model.api.GitHubApi
import com.geekbrains.tests.model.room.database.AppDatabase
import com.geekbrains.tests.repository.DatabaseRepository
import com.geekbrains.tests.repository.GitHubRepository
import com.geekbrains.tests.repository.RepositoryContract
import com.geekbrains.tests.view.details.DetailsViewModel
import com.geekbrains.tests.view.search.MainActivity
import com.geekbrains.tests.view.search.SearchViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { AppDatabase.getInstance() }
    single<GitHubApi> {
        Retrofit.Builder()
            .baseUrl(MainActivity.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GitHubApi::class.java)
    }
    single<RepositoryContract> { GitHubRepository(get()) }
    single { DatabaseRepository(get()) }
}


val mainModule = module {
    viewModel { SearchViewModel(get(), get()) }
}

val detailsModule = module {
    viewModel { DetailsViewModel(get()) }
}

