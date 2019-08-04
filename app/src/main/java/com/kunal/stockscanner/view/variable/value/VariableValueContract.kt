package com.kunal.stockscanner.view.variable.value

import com.kunal.stockscanner.view.base.BasePresenter
import com.kunal.stockscanner.view.base.BaseView

/**
 * Created by kunal on 2019-08-04.
 */
interface VariableValueContract {
    interface View:BaseView<Presenter>
    interface Presenter:BasePresenter<View>
}