package com.kunal.stockscanner.view.scans

import com.kunal.stockscanner.data.base.DataSource.GetItemsCallback
import com.kunal.stockscanner.data.base.Response
import com.kunal.stockscanner.data.scans.RemoteDataSource
import com.kunal.stockscanner.view.base.BasePresenterImpl
import com.kunal.stockscanner.view.scans.model.Scan
import javax.inject.Inject

/**
 * Created by kunal on 2019-07-27.
 */
class ScansPresenter @Inject constructor(private val remoteDataSource: RemoteDataSource): BasePresenterImpl<ScansContract.View>(), ScansContract.Presenter{

    override fun onStart() {
        super.onStart()
        view?.showLoader()
        remoteDataSource.getItems(object : GetItemsCallback<Scan> {
            override fun onFailure(response: Response) {
                view?.showError(response.description)
                view?.hideLoader()
            }

            override fun onSuccess(data: List<Scan>) {
                view?.setData(data)
                view?.hideLoader()
            }
        })
    }
}