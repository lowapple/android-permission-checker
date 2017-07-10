package com.plancatlog.pinpermission.PinPermission

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.util.Log
import java.util.jar.Manifest

/**
 * Created by plancatlog on 2017. 7. 10..
 */

class PinPermission(context: Activity) {
    private val context = context
    private val requestPermissions = arrayListOf<String>()
    private var permissionDenied: (() -> Unit)? = null
    private var permissionGranted: (() -> Unit)? = null

    fun checkPermission(permission: String, callback: ((Boolean) -> Unit)? = null): Boolean {
        val result = context.checkSelfPermission(permission) != PackageManager.PERMISSION_DENIED
        if (callback != null)
            callback(result)
        return result
    }

    fun setPermissionGranted(callback: () -> Unit): PinPermission {
        permissionGranted = callback
        return this
    }

    fun setPermissionDenied(callback: () -> Unit): PinPermission {
        permissionDenied = callback
        return this
    }

    fun addPermission(permission: String): PinPermission {
        requestPermissions.add(permission)
        return this
    }

    fun check() {
        ActivityCompat.requestPermissions(context, requestPermissions.toTypedArray(), 1)

        var firstDeniedPermission = true
        requestPermissions.forEachIndexed { index, s ->
            val permission = checkPermission(s)
            if (!permission)
                firstDeniedPermission = false
        }
        if (!firstDeniedPermission) {
            if (permissionDenied != null) {
                permissionDenied?.invoke()
            }
        } else {
            if (permissionGranted != null) {
                permissionGranted?.invoke()
            }
        }
    }
}