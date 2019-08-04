package com.kunal.stockscanner.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kunal.stockscanner.R
import com.kunal.stockscanner.view.criteria.CriteriaFragment
import com.kunal.stockscanner.view.scans.ScansFragment
import com.kunal.stockscanner.view.scans.ScansFragment.Companion
import com.kunal.stockscanner.view.scans.model.Scan
import com.kunal.stockscanner.view.variable.model.Variable
import com.kunal.stockscanner.view.variable.model.VariableIndicator
import com.kunal.stockscanner.view.variable.model.VariableValue
import com.kunal.stockscanner.view.variable.value.VariableValueFragment

class MainActivity : AppCompatActivity(), ScansFragment.OnScansInteractionListener, CriteriaFragment.OnCriteriaInteractionListener, VariableValueFragment.OnVariableInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.root, ScansFragment.newInstance())
            .commit()
    }

    override fun onScanSelected(scan: Scan) {
        val criteriaFragment = CriteriaFragment.newInstance(scan)
        supportFragmentManager.beginTransaction()
            .replace(R.id.root, criteriaFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onVariableSelected(variable: Variable) {
        val fragment = VariableValueFragment.newInstance(variable as VariableValue)
        /*when(variable){
            is VariableValue -> VariableValueFragment.newInstance(variable)
            is VariableIndicator -> VariableValueFragment.newInstance(variable)
        }*/
        supportFragmentManager.beginTransaction()
            .replace(R.id.root, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onVariableFragmentInteraction() {

    }
}
