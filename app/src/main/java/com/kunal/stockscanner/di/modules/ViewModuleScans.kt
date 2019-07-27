package com.kunal.stockscanner.di.modules

import com.kunal.stockscanner.view.scans.ScansContract
import com.kunal.stockscanner.view.scans.ScansPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by kunal on 2019-07-26.
 */
@Module
class ViewModuleScans {

    @Provides
    fun getScansPresenter(scansPresenter: ScansPresenter):ScansContract.Presenter = scansPresenter
}