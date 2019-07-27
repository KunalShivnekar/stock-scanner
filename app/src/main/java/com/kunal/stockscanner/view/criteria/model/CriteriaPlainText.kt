package com.kunal.stockscanner.view.criteria.model

/**
 * Created by kunal on 2019-07-26.
 */
data class CriteriaPlainText(val text:String):Criteria {

    override val type: String = "plain_text"
}