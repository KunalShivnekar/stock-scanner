package com.kunal.stockscanner.view.criteria

import com.kunal.stockscanner.view.base.BasePresenterImpl
import javax.inject.Inject

/**
 * Created by kunal on 2019-08-04.
 */
class CriteriaPresenter @Inject constructor():BasePresenterImpl<CriteriaContract.View>(), CriteriaContract.Presenter {

    override fun onStart() {
        super.onStart()
        view?.let {
            it.setScan(it.scan)
        }
    }
}