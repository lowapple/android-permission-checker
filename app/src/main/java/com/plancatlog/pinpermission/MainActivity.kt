package com.plancatlog.pinpermission

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.plancatlog.pinpermission.PinPermission.PinPermission

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("Log", PinPermission(this).checkPermission(Manifest.permission.WRITE_CALENDAR).toString())


        PinPermission(this)
                .addPermission(android.Manifest.permission.CAMERA)
                .addPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                .setPermissionGranted {
                    Toast.makeText(this, "Yeah", Toast.LENGTH_SHORT).show()
                }
                .setPermissionDenied {
                    Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()
                }
                .check()
    }
}