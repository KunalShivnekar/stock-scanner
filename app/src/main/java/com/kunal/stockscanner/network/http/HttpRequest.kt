package com.kunal.stockscanner.network.http

/**
 * Created by kunal on 2019-07-26.
 */
data class HttpRequest(val endpointUrl:String) {

    private var headers:Map<String,String> = HashMap()

    constructor(endpointUrl:String, headers:Map<String,String>):this(endpointUrl){
        this.headers = headers
    }
}