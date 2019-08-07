package com.kunal.stockscanner.view.scans.model

import android.os.Parcelable
import com.kunal.stockscanner.view.criteria.model.Criteria

/**
 * Created by kunal on 2019-07-26.
 */
interface Scan:Parcelable {
    val id:Int
    val name:String
    val tag:String
    val color:String
    val criteriaList:MutableList<Criteria>
}