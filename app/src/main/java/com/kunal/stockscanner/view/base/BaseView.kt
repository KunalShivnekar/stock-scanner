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
     * Shows a error message on View.
     *
     * @param resId The resource Id of the string to show
     * @param args  The values of placeholder in the string resource.
     */
    fun showError(resId: Int, vararg args: Any)

    /**
     * This method shows error message on the view.
     *
     * @param resId The resource Id of the string to show
     */
    fun showError(resId: Int)

    /**
     * Use this method to show progress bar on the view
     */
    fun showLoader()
}