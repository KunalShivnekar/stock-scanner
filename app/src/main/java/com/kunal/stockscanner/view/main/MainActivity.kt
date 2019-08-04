package com.kunal.stockscanner.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kunal.stockscanner.R
import com.kunal.stockscanner.view.base.BaseFragment
import com.kunal.stockscanner.view.base.BasePresenter
import com.kunal.stockscanner.view.base.BasePresenterImpl
import com.kunal.stockscanner.view.base.BaseView
import com.kunal.stockscanner.view.criteria.CriteriaFragment
import com.kunal.stockscanner.view.scans.ScansFragment
import com.kunal.stockscanner.view.scans.model.Scan
import com.kunal.stockscanner.view.variable.indicator.VariableIndicatorFragment
import com.kunal.stockscanner.view.variable.model.Variable
import com.kunal.stockscanner.view.variable.model.VariableIndicator
import com.kunal.stockscanner.view.variable.model.VariableValue
import com.kunal.stockscanner.view.variable.value.VariableValueFragment

class MainActivity : AppCompatActivity(), ScansFragment.OnScansInteractionListener, CriteriaFragment.OnCriteriaInteractionListener, VariableValueFragment.OnVariableValueInteractionListener, VariableIndicatorFragment.OnVariableIndicatorInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.root, ScansFragment.newInstance())
            .commit()
    }

    override fun onScanSelected(scan: Scan) {
        addFragmentToBackStack(CriteriaFragment.newInstance(scan))
    }

    override fun onVariableSelected(variable: Variable) {
        when(variable){
            is VariableValue -> addFragmentToBackStack(VariableValueFragment.newInstance(variable))
            is VariableIndicator -> addFragmentToBackStack(VariableIndicatorFragment.newInstance(variable))
        }
    }

    override fun onVariableValueFragmentInteraction() {
        // handle variable value selection here
    }

    override fun onVariableIndicatorFragmentInteraction() {
        // handle variable indicator selection here
    }

    private fun addFragmentToBackStack(fragment: BaseFragment<*>){
        supportFragmentManager.beginTransaction()
            .replace(R.id.root, fragment)
            .addToBackStack(null)
            .commit()
    }
}
