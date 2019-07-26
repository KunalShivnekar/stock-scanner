package com.kunal.stockscanner.network.http

/**
 * Created by kunal on 2019-07-26.
 */
data class HttpResponse(private val body:String, private val code:Int){

    private var exception:Exception? = null

    constructor(exception: Exception):this("",0){
        this.exception = exception
    }
}