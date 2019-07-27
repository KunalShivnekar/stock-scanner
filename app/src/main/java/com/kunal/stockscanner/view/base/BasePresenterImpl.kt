package com.kunal.stockscanner.view.base

import androidx.lifecycle.LifecycleOwner

/**
 * Created by kunal on 2019-07-27.
 */
abstract class BasePresenterImpl<T:BaseView<*>>:BasePresenter<T> {

    var view: T? = null
    protected lateinit var lifecycleOwner: LifecycleOwner

    final override fun attachView(view: T, lifecycleOwner: LifecycleOwner) {
        this.view = view
        this.lifecycleOwner = lifecycleOwner
    }

    override fun detachView() {
        view = null
    }
    /**
     * Lifecycle event listener for the view's Lifecycle
     */
    override fun onCreate() {
        //Base Implementation
    }

    /**
     * Lifecycle event listener for the view's Lifecycle
     */
    override fun onPause() {
        //Base Implementation
    }

    /**
     * Lifecycle event listener for the view's Lifecycle
     */
    override fun onResume() {
        //Base Implementation
    }

    /**
     * Lifecycle event listener for the view's Lifecycle
     */
    override fun onStart() {
        //Base Implementation
    }

    /**
     * Lifecycle event listener for the view's Lifecycle
     */
    override fun onStop() {
        //Base Implementation
    }

    /**
     * Lifecycle event listener for the view's Lifecycle
     */
    override fun onDestroy() {
        //Base Implementation
    }
}