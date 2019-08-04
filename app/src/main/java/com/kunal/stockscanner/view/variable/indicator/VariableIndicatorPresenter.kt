package com.kunal.stockscanner.view.variable.indicator

import com.kunal.stockscanner.view.base.BasePresenterImpl
import javax.inject.Inject

/**
 * Created by kunal on 2019-08-04.
 */
class VariableIndicatorPresenter @Inject constructor():BasePresenterImpl<VariableIndicatorContract.View>(), VariableIndicatorContract.Presenter {

    override fun onStart() {
        super.onStart()
        view?.apply { setParams(variableIndicator) }
    }
}