package com.kunal.stockscanner.data.base

import com.kunal.stockscanner.data.base.DataSource.GetItemsCallback

/**
 * Created by kunal on 2019-07-26.
 */
 abstract class DataSourceImpl<T>:DataSource<T> {

    override fun getItems(getItemsCallback: GetItemsCallback<T>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}