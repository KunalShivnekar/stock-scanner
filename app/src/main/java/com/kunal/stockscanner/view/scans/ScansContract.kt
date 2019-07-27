package com.kunal.stockscanner.view.scans

import com.kunal.stockscanner.view.base.BasePresenter
import com.kunal.stockscanner.view.base.BaseView
import com.kunal.stockscanner.view.scans.model.Scan

/**
 * Created by kunal on 2019-07-27.
 */
interface ScansContract {

    interface View:BaseView<Presenter>{
        fun setData(list:List<Scan>)
    }

    interface Presenter:BasePresenter<View>
}