package com.kunal.stockscanner.view.criteria

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kunal.stockscanner.R
import com.kunal.stockscanner.view.criteria.model.Criteria
import com.kunal.stockscanner.view.criteria.model.CriteriaPlainText
import com.kunal.stockscanner.view.criteria.model.CriteriaVariable
import com.kunal.stockscanner.view.scans.model.Scan
import com.kunal.stockscanner.view.variable.model.Variable
import kotlinx.android.synthetic.main.scan_item.view.content
import kotlinx.android.synthetic.main.scan_item.view.name

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
                    is CriteriaVariable -> view.name.text = value.text
                }

            }
    }

    interface OnVariableSelectedListener {
        fun onVariableSelected(variable: Variable)
    }
}