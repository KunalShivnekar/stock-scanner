package com.kunal.stockscanner.view.criteria.model

import kotlinx.android.parcel.Parcelize

/**
 * Created by kunal on 2019-07-26.
 */
@Parcelize
data class CriteriaPlainText(val text:String):Criteria {

    override val type: String = "plain_text"
}