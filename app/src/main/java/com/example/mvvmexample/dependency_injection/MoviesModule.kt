package com.example.mvvmexample.dependency_injection

import com.example.mvvmexample.repository.MoviesRepository
import com.example.mvvmexample.viewmodel.MoviesViewModel
import org.koin.dsl.module

val moviesModule = module {
    single {
        //We use the get() function to get a Koin instance that provides the MoviesService dependency
        MoviesRepository(_moviesServiceImpl = get())
    }
    single {
        //We use the get() function to get a Koin instance that provides the MoviesRepository dependency
        MoviesViewModel(_moviesRepository = get())
    }
}