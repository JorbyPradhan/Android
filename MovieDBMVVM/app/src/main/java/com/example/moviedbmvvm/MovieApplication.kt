package com.example.moviedbmvvm

import android.app.Application
import com.example.moviedbmvvm.data.db.AppDatabase
import com.example.moviedbmvvm.data.network.MovieApi
import com.example.moviedbmvvm.data.network.NetworkConnectionInterceptor
import com.example.moviedbmvvm.data.preferences.PreferenceProvider
import com.example.moviedbmvvm.data.repositories.MoviesRepository
import com.example.moviedbmvvm.ui.home.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MovieApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MovieApplication))

        bind() from singleton {
            NetworkConnectionInterceptor(instance())
        }
        bind() from singleton { MovieApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { MoviesRepository(instance(), instance(), instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
    }
}