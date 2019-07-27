package com.kunal.stockscanner.view.scans

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kunal.stockscanner.R

import com.kunal.stockscanner.view.scans.model.Scan

import kotlinx.android.synthetic.main.scan_item.view.*

class ScansRecyclerViewAdapter(private val listener: OnScanSelectedListener) : RecyclerView.Adapter<ScansRecyclerViewAdapter.ScanViewHolder>() {

    var contentList = listOf<Scan>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scan_item, parent, false)
        return ScanViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScanViewHolder, position: Int) {
        holder.item = contentList[position]
    }

    override fun getItemCount(): Int = contentList.size

    inner class ScanViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
         var item:Scan? = null
            set(value) {
                if(value == null) return
                field = value
                view.name.text = value.name
                view.content.text = value.tag
            }

        init {
            view.setOnClickListener{listener.onScanSelected(item!!)}
        }
    }

    interface OnScanSelectedListener {
        fun onScanSelected(scan:Scan)
    }
}
