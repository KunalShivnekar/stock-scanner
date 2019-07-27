package com.kunal.stockscanner.config

import android.app.Application
import com.kunal.stockscanner.di.components.AppComponent
import com.kunal.stockscanner.di.components.DaggerAppComponent
import com.kunal.stockscanner.di.components.DataComponent
import com.kunal.stockscanner.di.modules.AppModule

/**
 * Created by kunal on 2019-07-26.
 */
class StockScannerApplication : Application() {

    lateinit var appComponent:AppComponent

    lateinit var dataComponent: DataComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .build()

        appComponent.inject(this)

        dataComponent = appComponent.dataComponentBuilder.build()
    }
}