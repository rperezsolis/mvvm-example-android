package com.example.mvvmexample.dependency_injection

import com.example.mvvmexample.repository.MoviesService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {
        getRetrofit()
    }
    single {
        getMoviesServiceImpl(retrofit = get())
    }
}

fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

fun getMoviesServiceImpl(retrofit: Retrofit): MoviesService {
    return retrofit.create(MoviesService::class.java)
}
