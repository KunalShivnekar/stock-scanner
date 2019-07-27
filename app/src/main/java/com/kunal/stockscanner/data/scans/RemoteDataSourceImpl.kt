package com.kunal.stockscanner.data.scans

import com.google.gson.Gson
import com.kunal.stockscanner.data.base.DataSource.GetItemsCallback
import com.kunal.stockscanner.data.base.DataSourceImpl
import com.kunal.stockscanner.data.base.Response
import com.kunal.stockscanner.network.http.HttpClient
import com.kunal.stockscanner.network.http.HttpClient.ResponseCallback
import com.kunal.stockscanner.network.http.HttpRequest
import com.kunal.stockscanner.network.http.HttpResponse
import com.kunal.stockscanner.view.scans.model.Scan
import com.kunal.stockscanner.view.scans.model.ScanDefault
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

/**
 * Created by kunal on 2019-07-26.
 */
class RemoteDataSourceImpl @Inject constructor(private val httpClient: HttpClient, private val gson: Gson) : DataSourceImpl<Scan>(), RemoteDataSource {

    private val endPointURL:String = "https://mp-android-challenge.herokuapp.com/data"

    override fun getItems(getItemsCallback: GetItemsCallback<Scan>) {
        val request = HttpRequest(endPointURL)
        httpClient.get(request = request, callback = object : ResponseCallback {
            override fun onSuccess(httpResponse: HttpResponse) {
                val jsonResponse =  JSONArray(httpResponse.body)
                val scansList = ArrayList<Scan>()
                for(i in 0 until (jsonResponse.length()-1)){
                    scansList.add(gson.fromJson(jsonResponse[i].toString(), ScanDefault::class.java))
                }
                getItemsCallback.onSuccess(scansList)
            }

            override fun onFailure(httpResponse: HttpResponse) {
                getItemsCallback.onFailure(if (httpResponse.exception is IOException){
                    Response("Network Error")
                } else {
                    Response("Server Error")
                })
            }
        })
    }
}