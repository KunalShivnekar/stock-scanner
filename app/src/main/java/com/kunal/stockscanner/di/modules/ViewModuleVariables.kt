package com.kunal.stockscanner.di.modules

import com.kunal.stockscanner.view.variable.value.VariableValueContract
import com.kunal.stockscanner.view.variable.value.VariableValuePresenter
import dagger.Module
import dagger.Provides

/**
 * Created by kunal on 2019-07-26.
 */
@Module
class ViewModuleVariables {

    @Provides
    fun getVariableValuePresenter(presenter: VariableValuePresenter):VariableValueContract.Presenter = presenter
}