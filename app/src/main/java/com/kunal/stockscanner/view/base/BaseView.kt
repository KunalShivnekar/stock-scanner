package com.kunal.stockscanner.view.base

import androidx.lifecycle.LifecycleObserver

/**
 * Created by kunal on 2019-07-26.
 */
interface BaseView <T : LifecycleObserver> {

    /**
     * Use this method to attach a presenter to this view
     *
     * @param presenter the presenter to be attached
     */
    fun attachPresenter(presenter: T)

    /**
     * Use this method to hide progress bar on the view
     */
    fun hideLoader()

    /**
     * This method shows error message on the view.
     *
     * @param  error string to show
     */
    fun showError(error:String)

    /**
     * Use this method to show progress bar on the view
     */
    fun showLoader()
}