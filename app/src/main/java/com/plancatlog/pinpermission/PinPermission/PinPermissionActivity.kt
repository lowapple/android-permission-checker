package com.plancatlog.pinpermission.PinPermission

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.SearchEvent

class PinPermissionActivity : Activity() {
    val PIN_REQUEST = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(this, PinPermissionInstance.Instance().requestPermissions.toTypedArray(), PIN_REQUEST)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var permissionCheck = true
        grantResults.forEachIndexed { index, i ->
            if (permissionCheck)
                if (i == -1)
                    permissionCheck = false
        }
        if (!permissionCheck) {
            if (PinPermissionInstance.Instance().permissionDenied != null)
                PinPermissionInstance.Instance().permissionDenied?.invoke()
        } else {
            if (PinPermissionInstance.Instance().permissionGranted != null)
                PinPermissionInstance.Instance().permissionGranted?.invoke()
        }
        finish()
    }
}