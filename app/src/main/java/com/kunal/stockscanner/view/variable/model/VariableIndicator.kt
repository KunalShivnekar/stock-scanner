package com.kunal.stockscanner.view.variable.model

/**
 * Created by kunal on 2019-07-26.
 */
data class VariableIndicator(override val key: String,
    val studyType:String,
    val parameterName:String,
    val minValue:Int,
    val maxValue:Int,
    val defaultValue:Int):Variable {

    override val type: String = "indicator"
}