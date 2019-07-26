package com.kunal.stockscanner.data.base

/**
 * Created by kunal on 2019-07-26.
 */
interface DataSource<T> {

    interface GetItemsCallback<T> {

        fun onFailure(response: Response)

        fun onSuccess(data: List<T>)
    }

    fun getItems(getItemsCallback: GetItemsCallback<T>)
}