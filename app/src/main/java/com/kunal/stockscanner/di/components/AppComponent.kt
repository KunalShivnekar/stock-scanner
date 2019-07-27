package com.kunal.stockscanner.di.components

import com.kunal.stockscanner.config.StockScannerApplication
import com.kunal.stockscanner.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by kunal on 2019-07-26.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
    }

    val dataComponentBuilder: DataComponent.Builder

    fun inject(stockScannerApplication: StockScannerApplication)
}