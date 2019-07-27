package com.kunal.stockscanner.view.scans.model

import com.kunal.stockscanner.view.criteria.model.Criteria

/**
 * Created by kunal on 2019-07-26.
 */
interface Scan {
    val id:Int
    val name:String
    val tag:String
    val color:String
    val criteriaList:List<Criteria>
}