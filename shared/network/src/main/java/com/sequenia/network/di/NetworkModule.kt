package com.sequenia.network.di

import com.sequenia.network.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
	single { provideRetrofit() }
}

fun provideRetrofit(): Retrofit {
	val moshi: Moshi = Moshi.Builder()
		.add(KotlinJsonAdapterFactory())
		.build()

	return Retrofit.Builder()
		.baseUrl(BuildConfig.API_URL)
		.addConverterFactory(MoshiConverterFactory.create(moshi))
		.build()
}