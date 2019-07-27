package com.kunal.stockscanner.data.scans

import com.google.gson.Gson
import com.kunal.stockscanner.data.base.DataSource.GetItemsCallback
import com.kunal.stockscanner.data.base.DataSourceImpl
import com.kunal.stockscanner.data.base.Response
import com.kunal.stockscanner.network.http.HttpClient
import com.kunal.stockscanner.network.http.HttpClient.ResponseCallback
import com.kunal.stockscanner.network.http.HttpRequest
import com.kunal.stockscanner.network.http.HttpResponse
import com.kunal.stockscanner.view.criteria.model.Criteria
import com.kunal.stockscanner.view.criteria.model.CriteriaPlainText
import com.kunal.stockscanner.view.criteria.model.CriteriaVariable
import com.kunal.stockscanner.view.scans.model.Scan
import com.kunal.stockscanner.view.scans.model.ScanDefault
import com.kunal.stockscanner.view.variable.model.Variable
import com.kunal.stockscanner.view.variable.model.VariableIndicator
import com.kunal.stockscanner.view.variable.model.VariableValue
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
                for(i in 0 until (jsonResponse.length())){
                    val jsonItem:JSONObject = jsonResponse.getJSONObject(i)


                    val criteriaList = ArrayList<Criteria>()
                    val criteriaArray = jsonItem.getJSONArray("criteria")
                    for (j in 0 until criteriaArray.length()){
                        val criteriaJson = criteriaArray.getJSONObject(j)
                        when(criteriaJson.getString("type")){
                            "plain_text" -> {
                                criteriaList.add(CriteriaPlainText(
                                    text = criteriaJson.getString("text")
                                ))
                            }
                            "variable" -> {
                                val variableJson = criteriaJson.getJSONObject("variable")
                                val variableMap = hashMapOf<String,Variable>()
                                for (key in variableJson.keys()){
                                    val variableItem = variableJson.getJSONObject(key)
                                    when(variableItem.getString("type")){
                                        "value" -> {
                                            val values = ArrayList<Int>()
                                            for (k in 0 until variableItem.getJSONArray("values").length()) {
                                                values.add(variableItem.getJSONArray("values").getInt(k))
                                            }
                                            variableMap[key] = VariableValue(key = key, values = values)
                                        }
                                        "indicator" -> {
                                            variableMap[key] = VariableIndicator(
                                                key = key,
                                                studyType = variableItem.getString("study_type"),
                                                parameterName = variableItem.getString("parameter_name"),
                                                minValue = variableItem.getInt("min_value"),
                                                maxValue = variableItem.getInt("max_value"),
                                                defaultValue = variableItem.getInt("default_value")
                                            )
                                        }
                                    }
                                }
                                criteriaList.add(CriteriaVariable(
                                    text = criteriaJson.getString("text"),
                                    variableMap = variableMap
                                ))
                            }
                        }
                    }

                    val scan = ScanDefault(id = jsonItem.getInt("id"),
                        name = jsonItem.getString("name"),
                        tag = jsonItem.getString("tag"),
                        color = jsonItem.getString("color"),
                        criteriaList = criteriaList)
                    scansList.add(scan)
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