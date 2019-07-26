package com.kunal.stockscanner.di.modules

import android.content.Context
import com.kunal.stockscanner.di.components.DataComponent
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

}