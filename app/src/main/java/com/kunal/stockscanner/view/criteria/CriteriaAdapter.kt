package com.kunal.stockscanner.view.criteria

import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kunal.stockscanner.R
import com.kunal.stockscanner.view.criteria.model.Criteria
import com.kunal.stockscanner.view.criteria.model.CriteriaPlainText
import com.kunal.stockscanner.view.criteria.model.CriteriaVariable
import com.kunal.stockscanner.view.scans.model.Scan
import com.kunal.stockscanner.view.variable.model.Variable
import kotlinx.android.synthetic.main.scan_item.view.content
import kotlinx.android.synthetic.main.scan_item.view.name
import java.util.regex.Pattern

/**
 * Created by kunal on 2019-07-28.
 */
class CriteriaAdapter(private val listener: OnVariableSelectedListener) : RecyclerView.Adapter<CriteriaAdapter.CriteriaViewHolder>() {

    var contentList = listOf<Criteria>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriteriaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scan_item, parent, false)
        return CriteriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CriteriaViewHolder, position: Int) {
        holder.item = contentList[position]
    }

    override fun getItemCount(): Int = contentList.size

    inner class CriteriaViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var item: Criteria? = null
            set(value) {
                field = value
                when (value) {
                    is CriteriaPlainText -> view.name.text = value.text
                    is CriteriaVariable ->{
                        view.name.text = buildVariableText(value)
                        view.name.movementMethod = LinkMovementMethod.getInstance()
                    }
                }

            }
    }

    private fun buildVariableText(value:CriteriaVariable):CharSequence{
        var variableText = value.text
        val variableArray = value.text.split(" ").toMutableList()
        for (word in variableArray){
            if(word.matches("[\$][0-9]+".toRegex())){
                variableText = variableText.replace(word,value.variableMap.getValue(word).default)
            }
        }
        val spannableString = SpannableString(variableText)
        var index = 0
        for (word in variableArray){
            if(word.matches("[\$][0-9]+".toRegex())){
                val variable = value.variableMap.getValue(word)
                val clickableSpan = object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        listener.onVariableSelected(variable)
                    }
                }
                spannableString.setSpan(clickableSpan,index,index+variable.default.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                index += variable.default.length+1
            } else {
                index += word.length + 1
            }
        }
        return spannableString
    }

    interface OnVariableSelectedListener {
        fun onVariableSelected(variable: Variable)
    }
}