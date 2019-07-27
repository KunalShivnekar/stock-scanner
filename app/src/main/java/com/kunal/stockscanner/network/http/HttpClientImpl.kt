package com.kunal.stockscanner.network.http

import com.kunal.stockscanner.network.http.HttpClient.ResponseCallback
import com.kunal.stockscanner.utility.AppExecutors
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import kotlin.collections.Map.Entry

/**
 * Created by kunal on 2019-07-26.
 */
class HttpClientImpl @Inject constructor(private val appExecutors: AppExecutors):HttpClient {

    override val bodyContentTypeJson: String = "application/json; charset=utf-8"

    override fun get(request: HttpRequest, callback: ResponseCallback) {
        appExecutors.networkIO().execute {
            val builder = Request.Builder()
                .url(request.endpointUrl)

            for (entry:Entry<String,String>  in request.headers.entries) {
                builder.addHeader(entry.key, entry.value)
            }

            val client = OkHttpClient().newBuilder().build()
            client.newCall(builder.build()).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    appExecutors.mainThread().execute { callback.onFailure(HttpResponse(e)) }
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()?:""
                    val code = response.code
                    if (response.isSuccessful) {
                        appExecutors.mainThread().execute { callback.onSuccess(HttpResponse(code, body)) }
                    } else {
                        appExecutors.mainThread().execute { callback.onFailure(HttpResponse(code, body)) }
                    }
                }
            })
        }
    }
}