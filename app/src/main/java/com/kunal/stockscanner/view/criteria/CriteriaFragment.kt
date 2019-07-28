package com.kunal.stockscanner.view.criteria

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.kunal.stockscanner.R
import com.kunal.stockscanner.view.scans.model.Scan
import com.kunal.stockscanner.view.variable.model.Variable
import kotlinx.android.synthetic.main.fragment_criteria.view.criteria_list
import kotlinx.android.synthetic.main.fragment_criteria.view.scan_name
import kotlinx.android.synthetic.main.fragment_criteria.view.scan_tag

private const val ARG_SCAN = "ARG_SCAN"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CriteriaFragment.OnCriteriaInteractionListener] interface
 * to handle interaction events.
 * Use the [CriteriaFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CriteriaFragment : Fragment(), CriteriaAdapter.OnVariableSelectedListener {

    private var listener: OnCriteriaInteractionListener? = null

    private val criteriaAdapter = CriteriaAdapter(this)

    private val scan: Scan by lazy { requireNotNull(arguments).getParcelable(ARG_SCAN) as Scan }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_criteria, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.criteria_list as RecyclerView

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = criteriaAdapter
        }

        setScan(scan)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCriteriaInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnCriteriaInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onVariableSelected(variable: Variable) {
        listener?.onVariableSelected(variable)
    }

    private fun setScan(scan: Scan){
        view?.let {
            it.scan_name?.text = scan.name
            it.scan_tag?.text = scan.tag
            criteriaAdapter.contentList = scan.criteriaList
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnCriteriaInteractionListener {
        fun onVariableSelected(variable: Variable)
    }

    companion object {
        @JvmStatic
        fun newInstance(scan:Scan) = CriteriaFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_SCAN, scan)
            }
        }
    }
}
