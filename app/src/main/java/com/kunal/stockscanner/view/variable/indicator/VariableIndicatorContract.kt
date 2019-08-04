package com.kunal.stockscanner.view.variable.indicator

import com.kunal.stockscanner.view.base.BasePresenter
import com.kunal.stockscanner.view.base.BaseView
import com.kunal.stockscanner.view.variable.model.VariableIndicator

/**
 * Created by kunal on 2019-08-04.
 */
interface VariableIndicatorContract {
    interface View:BaseView<Presenter>{
        val variableIndicator:VariableIndicator

        fun setParams(variableIndicator:VariableIndicator)
    }
    interface Presenter:BasePresenter<View>
}