package com.kunal.stockscanner.view.variable.model

/**
 * Created by kunal on 2019-07-26.
 */
data class VariableValue(override val key: String, val values:List<Int>):Variable {

    override val type: String = "value"
}