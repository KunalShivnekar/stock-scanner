package com.kunal.stockscanner.view.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kunal.stockscanner.R
import com.kunal.stockscanner.config.StockScannerApplication
import com.kunal.stockscanner.di.components.ViewComponent
import javax.inject.Inject

/**
 * Created by kunal on 2019-07-27.
 */
abstract class BaseFragment<T:BasePresenter<*>>:Fragment(),BaseView<T> {

    protected lateinit var presenter:T
    private lateinit var progressBar: ProgressBar
    protected lateinit var injector: ViewComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            injector = (it.application as StockScannerApplication).dataComponent.viewComponentBuilder.build()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = ProgressBar(context,null, android.R.attr.indeterminateProgressStyle)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        lifecycle.removeObserver(presenter)
    }
    /**
     * Use this method to attach a presenter to this view
     *
     * @param presenter the presenter to be attached
     */
    @Inject
    override fun attachPresenter(presenter: T) {
        this.presenter = presenter
        lifecycle.addObserver(presenter)
    }

    override fun showError(error:String) {
        showToast(error)
    }

    /**
     * Use this method to show progress bar on the view
     */
    override fun showLoader() {
        progressBar.visibility = View.VISIBLE
    }

    /**
     * Use this method to hide progress bar on the view
     */
    override fun hideLoader() {
        progressBar.visibility = View.GONE
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}