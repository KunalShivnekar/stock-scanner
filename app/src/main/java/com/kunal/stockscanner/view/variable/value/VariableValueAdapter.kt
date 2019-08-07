package com.kunal.stockscanner.view.variable.value

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kunal.stockscanner.R

import com.kunal.stockscanner.view.variable.value.VariableValueAdapter.ViewHolder

import kotlinx.android.synthetic.main.variable_value_item.view.item_number

/**
 * [RecyclerView.Adapter] that can display a [values] and makes a call to the
 * specified [OnVariableValueSelectedListener].
 */
class VariableValueAdapter( private val values: List<Int>, private val listener: OnVariableValueSelectedListener) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.variable_value_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.variableValue = values[position]
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val valueView: TextView = view.item_number

        var variableValue:Int = 0
        set(value) {
            field = value
            valueView.text = value.toString()
        }

        init {
            valueView.setOnClickListener { listener.onVariableValueSelected(variableValue) }
        }
    }
    
    interface OnVariableValueSelectedListener{
        fun onVariableValueSelected(value:Int)
    }
}
