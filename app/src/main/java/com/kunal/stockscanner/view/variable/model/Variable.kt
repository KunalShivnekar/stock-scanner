package com.kunal.stockscanner.view.variable.model

import android.os.Parcelable

/**
 * Created by kunal on 2019-07-26.
 */
interface Variable:Parcelable {
    val key:String
    val type:String
}