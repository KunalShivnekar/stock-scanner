package com.kunal.stockscanner.network.http

/**
 * Created by kunal on 2019-07-26.
 */
data class HttpResponse(val code:Int, val body:String){

    var exception:Exception? = null

    constructor(exception: Exception):this(0,""){
        this.exception = exception
    }
}