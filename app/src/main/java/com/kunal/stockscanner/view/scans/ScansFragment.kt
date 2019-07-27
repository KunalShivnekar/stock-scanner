package com.kunal.stockscanner.view.scans

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kunal.stockscanner.R
import com.kunal.stockscanner.view.base.BaseFragment

import com.kunal.stockscanner.view.scans.model.Scan

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ScansFragment.OnScansInteractionListener] interface.
 */
class ScansFragment : BaseFragment<ScansContract.Presenter>(), ScansContract.View, ScansRecyclerViewAdapter.OnScanSelectedListener {

    private var listener: OnScansInteractionListener? = null

    private val scansRecyclerViewAdapter = ScansRecyclerViewAdapter(this@ScansFragment)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_scans, container, false) as RecyclerView
        with(view) {
            layoutManager = LinearLayoutManager(context)
            adapter = scansRecyclerViewAdapter
        }
        return view
    }

    override fun setData(list: List<Scan>) {
        scansRecyclerViewAdapter.contentList = list
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        presenter.attachView(this,this)
        if (context is OnScansInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnScansInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onScanSelected(scan: Scan) {
        listener?.onScanSelected(scan)
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    interface OnScansInteractionListener {
        fun onScanSelected(scan:Scan)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ScansFragment()
    }
}
