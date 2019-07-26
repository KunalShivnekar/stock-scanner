package com.kunal.stockscanner.di.components

import com.kunal.stockscanner.di.modules.ViewModuleCriteria
import com.kunal.stockscanner.di.modules.ViewModuleScans
import com.kunal.stockscanner.di.modules.ViewModuleVariables
import dagger.Subcomponent

/**
 * Created by kunal on 2019-07-26.
 */
@Subcomponent(modules = [ViewModuleCriteria::class, ViewModuleScans::class, ViewModuleVariables::class])
interface ViewComponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): ViewComponent
    }
}