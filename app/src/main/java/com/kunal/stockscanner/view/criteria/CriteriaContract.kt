package com.kunal.stockscanner.view.criteria

import com.kunal.stockscanner.view.base.BasePresenter
import com.kunal.stockscanner.view.base.BaseView
import com.kunal.stockscanner.view.scans.model.Scan

/**
 * Created by kunal on 2019-08-04.
 */
interface CriteriaContract {

    interface View: BaseView<Presenter> {
        val scan: Scan
        fun setScan(scan: Scan)
    }

    interface Presenter: BasePresenter<View>
}