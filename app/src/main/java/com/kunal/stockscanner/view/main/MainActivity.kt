package com.kunal.stockscanner.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kunal.stockscanner.R
import com.kunal.stockscanner.view.base.BaseFragment
import com.kunal.stockscanner.view.base.BasePresenter
import com.kunal.stockscanner.view.base.BasePresenterImpl
import com.kunal.stockscanner.view.base.BaseView
import com.kunal.stockscanner.view.criteria.CriteriaFragment
import com.kunal.stockscanner.view.criteria.model.CriteriaVariable
import com.kunal.stockscanner.view.scans.ScansFragment
import com.kunal.stockscanner.view.scans.model.Scan
import com.kunal.stockscanner.view.variable.indicator.VariableIndicatorFragment
import com.kunal.stockscanner.view.variable.model.Variable
import com.kunal.stockscanner.view.variable.model.VariableIndicator
import com.kunal.stockscanner.view.variable.model.VariableValue
import com.kunal.stockscanner.view.variable.value.VariableValueFragment

class MainActivity : AppCompatActivity(), ScansFragment.OnScansInteractionListener, CriteriaFragment.OnCriteriaInteractionListener, VariableValueFragment.OnVariableValueInteractionListener, VariableIndicatorFragment.OnVariableIndicatorInteractionListener {

    lateinit var criteriaFragment:CriteriaFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.root, ScansFragment.newInstance())
            .commit()
    }

    override fun onScanSelected(scan: Scan) {
        criteriaFragment = CriteriaFragment.newInstance(scan)
        addFragmentToBackStack(criteriaFragment)
    }

    override fun onVariableSelected(criteria: CriteriaVariable,variable: Variable) {
        when(variable){
            is VariableValue ->{
                addFragmentToBackStack(VariableValueFragment.newInstance(criteria, variable))
            }
            is VariableIndicator -> {

                addFragmentToBackStack(VariableIndicatorFragment.newInstance(criteria, variable))}
        }
    }

    override fun onVariableValueFragmentInteraction(criteria: CriteriaVariable,variable: Variable) {
        criteriaFragment.updateVariableValue(criteria,variable)
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
