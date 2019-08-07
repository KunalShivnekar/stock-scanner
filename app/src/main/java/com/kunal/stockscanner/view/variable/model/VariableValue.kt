package com.kunal.stockscanner.view.variable.model

import kotlinx.android.parcel.Parcelize

/**
 * Created by kunal on 2019-07-26.
 */
@Parcelize
data class VariableValue(override val key: String, val values:List<Int>):Variable {

    override val type: String = "value"

    override val default: String
        get() = values[0].toString()
    override var selected: String = default
}