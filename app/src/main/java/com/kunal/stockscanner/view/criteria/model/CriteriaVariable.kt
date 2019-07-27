package com.kunal.stockscanner.view.criteria.model

import com.kunal.stockscanner.view.variable.model.Variable

/**
 * Created by kunal on 2019-07-26.
 */
data class CriteriaVariable(val text:String, val variableMap: Map<String,Variable>):Criteria {

    override val type: String = "variable"
}