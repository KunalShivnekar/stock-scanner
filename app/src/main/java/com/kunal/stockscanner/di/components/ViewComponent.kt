package com.kunal.stockscanner.di.components

import com.kunal.stockscanner.di.modules.ViewModuleCriteria
import com.kunal.stockscanner.di.modules.ViewModuleScans
import com.kunal.stockscanner.di.modules.ViewModuleVariables
import com.kunal.stockscanner.view.criteria.CriteriaFragment
import com.kunal.stockscanner.view.scans.ScansFragment
import com.kunal.stockscanner.view.variable.value.VariableValueFragment
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

    fun inject(scansFragment: ScansFragment)

    fun inject(criteriaFragment: CriteriaFragment)

    fun inject(variableValueFragment: VariableValueFragment)
}