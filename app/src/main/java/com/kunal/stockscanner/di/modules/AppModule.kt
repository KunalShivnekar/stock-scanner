package com.kunal.stockscanner.di.modules

import android.content.Context
import com.kunal.stockscanner.di.components.DataComponent
import com.kunal.stockscanner.network.http.HttpClient
import com.kunal.stockscanner.network.http.HttpClientImpl
import dagger.Module
import dagger.Provides

/**
 * Created by kunal on 2019-07-26.
 */
@Module(subcomponents = [DataComponent::class])
class AppModule(private val context: Context) {

    @Provides
    fun getContext(): Context {
        return context
    }

    @Provides
    internal fun getHttpApiClient(httpClientImpl: HttpClientImpl): HttpClient {
        return httpClientImpl
    }
}