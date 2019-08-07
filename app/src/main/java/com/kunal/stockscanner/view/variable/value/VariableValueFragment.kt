package com.kunal.stockscanner.view.variable.value

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kunal.stockscanner.R
import com.kunal.stockscanner.view.base.BaseFragment
import com.kunal.stockscanner.view.criteria.model.CriteriaVariable
import com.kunal.stockscanner.view.variable.model.Variable
import com.kunal.stockscanner.view.variable.model.VariableValue
import kotlinx.android.synthetic.clearFindViewByIdCache
import kotlinx.android.synthetic.main.fragment_variable_indicator.view.parameter_name

private const val ARG_VARIABLE = "ARG_VARIABLE"
private const val ARG_CRITERIA = "ARG_CRITERIA"

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [VariableValueFragment.OnVariableValueInteractionListener] interface.
 */
class VariableValueFragment : BaseFragment<VariableValueContract.Presenter>(), VariableValueContract.View, VariableValueAdapter.OnVariableValueSelectedListener {

    private var listener: OnVariableValueInteractionListener? = null

    private val variableValue: VariableValue by lazy { requireNotNull(arguments).getParcelable(ARG_VARIABLE) as VariableValue }

    private val criteria: CriteriaVariable by lazy { requireNotNull(arguments).getParcelable(ARG_CRITERIA) as CriteriaVariable }

    lateinit var variableValueAdapter: VariableValueAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_variablevalue, container, false) as RecyclerView
        variableValueAdapter = VariableValueAdapter(variableValue.values, this@VariableValueFragment)
        with(view) {
            layoutManager = LinearLayoutManager(context)
            adapter = variableValueAdapter
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        presenter.attachView(this,this)
        if (context is OnVariableValueInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnVariableValueInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onVariableValueSelected(value: Int) {
        variableValue.selected = value.toString()
        listener?.onVariableValueFragmentInteraction(criteria, variableValue)
        activity?.onBackPressed()
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnVariableValueInteractionListener {

        fun onVariableValueFragmentInteraction(criteria: CriteriaVariable,variable: Variable)
    }

    companion object {
        @JvmStatic
        fun newInstance(criteria: CriteriaVariable, variableValue: VariableValue) =
            VariableValueFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_VARIABLE, variableValue)
                    putParcelable(ARG_CRITERIA, criteria)
                }
            }
    }
}
