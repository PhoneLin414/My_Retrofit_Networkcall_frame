package com.example.pla.my_retrofit_networkcall_frame.network

import com.example.pla.my_retrofit_networkcall_frame.BuildConfig
import com.example.pla.my_retrofit_networkcall_frame.config.Config
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceProvider {

    private const val TIMEOUT: Long = 60L

    fun <T> getService(services: Class<T>): T {
        return getRetrofit().create(services)
    }


    private fun getRetrofit(): Retrofit {

        val retrofitBuilder = Retrofit.Builder()

        retrofitBuilder.baseUrl(Config.BASE_URL)
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create())
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        retrofitBuilder.client(getOkHttpClient())

        return retrofitBuilder.build()

    }

    private fun getOkHttpClient(): OkHttpClient {

        val okHttpClient = OkHttpClient.Builder()

        okHttpClient.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.readTimeout(TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(TIMEOUT, TimeUnit.SECONDS)
       // okHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        if (!BuildConfig.BASE_URL){

           okHttpClient.interceptors().add(getInterceptor())

        }

        okHttpClient.interceptors().add(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        return okHttpClient.build()
    }

    private fun getInterceptor(): Interceptor {

        return Interceptor { chain ->

            val request = chain.request().newBuilder()
            request.addHeader("KEY", "value")

            chain.proceed(request.build())

        }

    }


}