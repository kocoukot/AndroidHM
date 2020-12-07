package com.example.androidhomework

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.example.androidhomework.location.LocationFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivityPermissions : AppCompatActivity() {

    private var rationaleDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissionGranted = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (permissionGranted) {
            noPermissionView.isVisible = false
            if (savedInstanceState == null) {
                ifPermissionGranted()
            }

        }

        getPermission.setOnClickListener {
            val needRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            if (needRationale) {
                showRationaleDialog()
            } else {
                getLocationPermission()
            }
        }


    }


    private fun checkPermission() {

    }

    private fun showRationaleDialog() {
        rationaleDialog = AlertDialog.Builder(this)
            .setMessage("Необходимо получение геолокации")
            .setPositiveButton("OK") { _, _ -> getLocationPermission() }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            ifPermissionGranted()
        } else {

            val needRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            if (needRationale) {
                showRationaleDialog()
            } else {
                getLocationPermission()
            }
        }
    }

    private fun ifPermissionGranted() {
        supportFragmentManager.beginTransaction()
            .add(R.id.frameForListFragment,
                LocationFragment()
            )
            .commit()
        noPermissionView.isVisible = false
    }

    override fun onPause() {
        super.onPause()
        onSaveInstanceState(Bundle())
    }

    override fun onDestroy() {
        super.onDestroy()
        rationaleDialog?.dismiss()
        rationaleDialog = null
    }

    private fun getLocationPermission() {
        requestPermissions(
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_CODE
        )
    }

    fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1
    }

}