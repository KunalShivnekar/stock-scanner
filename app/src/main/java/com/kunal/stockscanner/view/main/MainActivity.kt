package com.kunal.stockscanner.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kunal.stockscanner.R
import com.kunal.stockscanner.view.criteria.CriteriaFragment
import com.kunal.stockscanner.view.scans.ScansFragment
import com.kunal.stockscanner.view.scans.ScansFragment.Companion
import com.kunal.stockscanner.view.scans.model.Scan

class MainActivity : AppCompatActivity(), ScansFragment.OnScansInteractionListener, CriteriaFragment.OnCriteriaInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.root, ScansFragment())
            .commit()
    }

    override fun onScanSelected(scan: Scan) {
        val criteriaFragment = CriteriaFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.root, criteriaFragment)
            .addToBackStack(null)
            .commit()
        criteriaFragment.setScan(scan)
    }

    override fun onCriteriaInteraction(scan: Scan) {

    }
}
