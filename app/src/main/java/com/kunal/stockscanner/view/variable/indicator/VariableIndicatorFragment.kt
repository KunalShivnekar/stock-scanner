package com.kunal.stockscanner.view.variable.indicator

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kunal.stockscanner.R
import com.kunal.stockscanner.view.base.BaseFragment
import com.kunal.stockscanner.view.variable.model.VariableIndicator
import kotlinx.android.synthetic.main.fragment_variable_indicator.view.indicator_name
import kotlinx.android.synthetic.main.fragment_variable_indicator.view.parameter_name
import kotlinx.android.synthetic.main.fragment_variable_indicator.view.parameter_value

private const val ARG_VARIABLE = "ARG_VARIABLE"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [VariableIndicatorFragment.OnVariableIndicatorInteractionListener] interface
 * to handle interaction events.
 * Use the [VariableIndicatorFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class VariableIndicatorFragment :BaseFragment<VariableIndicatorContract.Presenter>(),VariableIndicatorContract.View {
    
    private var listener: OnVariableIndicatorInteractionListener? = null

    override val variableIndicator:VariableIndicator by lazy { requireNotNull(arguments).getParcelable(ARG_VARIABLE) as VariableIndicator }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_variable_indicator, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        presenter.attachView(this,this)
        if (context is OnVariableIndicatorInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnVariableIndicatorInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun setParams(variableIndicator: VariableIndicator) {
        view?.apply{
            indicator_name.text = variableIndicator.studyType
            parameter_name.text = variableIndicator.parameterName
            parameter_value.setText(variableIndicator.defaultValue.toString())
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
    interface OnVariableIndicatorInteractionListener {

        fun onVariableIndicatorFragmentInteraction()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param variableIndicator
         * @return A new instance of fragment VariableIndicatorFragment.
         */
        @JvmStatic
        fun newInstance(variableIndicator: VariableIndicator) =
            VariableIndicatorFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_VARIABLE, variableIndicator)
                }
            }
    }
}
