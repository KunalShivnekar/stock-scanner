package com.kunal.stockscanner.di.components

import com.kunal.stockscanner.di.modules.DataModule
import dagger.Subcomponent

/**
 * Created by kunal on 2019-07-26.
 */
@Subcomponent(modules = [DataModule::class])
interface DataComponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): DataComponent

    }

    val viewComponentBuilder: ViewComponent.Builder
}