package com.kunal.stockscanner.di.modules

import com.kunal.stockscanner.view.criteria.CriteriaContract
import com.kunal.stockscanner.view.criteria.CriteriaPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by kunal on 2019-07-26.
 */
@Module
class ViewModuleCriteria {

    @Provides
    fun getCriteriaPresenter(criteriaPresenter: CriteriaPresenter): CriteriaContract.Presenter = criteriaPresenter
}