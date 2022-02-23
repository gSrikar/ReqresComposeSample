package com.gsrikar.reqressample.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Retrofit provider
 */
object RetrofitProvider {

    /**
     * Base Url
     */
    private const val BASE_URL = "https://reqres.in/api/"

    // TODO: Inject variables using Hilt
    /**
     * Retrofit instance
     */
    @Synchronized
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .client(getOkHttpClient())
            .build()
    }

    /**
     * OkHttp Client instance
     */
    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor())
            .build()
    }

    /**
     * Logging interceptor
     */
    private fun getLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }

    /**
     * Gson instance
     */
    private fun getGson() = Gson()

    /**
     * User API Instance
     */
    val userInterface = getRetrofit().create(UserInterface::class.java)

}