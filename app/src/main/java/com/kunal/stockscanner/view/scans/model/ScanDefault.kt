package com.kunal.stockscanner.view.scans.model

import com.kunal.stockscanner.view.criteria.model.Criteria

/**
 * Created by kunal on 2019-07-26.
 */
data class ScanDefault(
    override val id: Int,
    override val name: String,
    override val tag: String,
    override val color: String,
    override val criteriaList: List<Criteria>):Scan