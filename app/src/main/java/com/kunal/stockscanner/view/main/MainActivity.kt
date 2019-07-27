package com.kunal.stockscanner.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kunal.stockscanner.R
import com.kunal.stockscanner.view.scans.ScansFragment
import com.kunal.stockscanner.view.scans.ScansFragment.Companion
import com.kunal.stockscanner.view.scans.model.Scan

class MainActivity : AppCompatActivity(), ScansFragment.OnScansInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onScanSelected(scan: Scan) {

    }
}
