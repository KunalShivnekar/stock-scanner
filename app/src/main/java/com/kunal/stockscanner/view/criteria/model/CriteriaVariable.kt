package com.kunal.stockscanner.view.criteria.model

import com.kunal.stockscanner.view.variable.model.Variable
import kotlinx.android.parcel.Parcelize

/**
 * Created by kunal on 2019-07-26.
 */
@Parcelize
data class CriteriaVariable(val text:String, val variableMap: MutableMap<String,Variable>):Criteria {

    override val type: String = "variable"
}