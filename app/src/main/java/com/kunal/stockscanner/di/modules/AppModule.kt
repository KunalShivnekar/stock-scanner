package com.kunal.stockscanner.di.modules

import android.content.Context
import com.google.gson.Gson
import com.kunal.stockscanner.di.components.DataComponent
import com.kunal.stockscanner.network.http.HttpClient
import com.kunal.stockscanner.network.http.HttpClientImpl
import dagger.Module
import dagger.Provides

/**
 * Created by kunal on 2019-07-26.
 */
@Module(subcomponents = [DataComponent::class])
class AppModule {

    @Provides
    fun getHttpApiClient(httpClientImpl: HttpClientImpl): HttpClient = httpClientImpl

    @Provides
    fun getGson(): Gson = Gson()
}