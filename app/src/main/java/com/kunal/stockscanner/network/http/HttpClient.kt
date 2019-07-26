package com.kunal.stockscanner.network.http

/**
 * Created by kunal on 2019-07-26.
 */
interface HttpClient {

    interface ResponseCallback {

        fun onSuccess(httpResponse: HttpResponse)

        fun onFailure(httpResponse: HttpResponse)
    }
    val bodyContentTypeJson: String

    fun get(request: HttpRequest, callback: ResponseCallback)

}