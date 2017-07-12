package com.plancatlog.pinpermission.PinPermission

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log

/**
 * Created by plancatlog on 2017. 7. 10..
 */

class PinPermission(context: Activity) {
    private val context = context

    init {
        PinPermissionInstance.Instance().requestPermissions.clear()
    }

    fun checkPermission(permission: String, callback: ((Boolean) -> Unit)? = null): Boolean {
        val result = context.checkSelfPermission(permission) != PackageManager.PERMISSION_DENIED
        if (callback != null)
            callback(result)
        return result
    }

    fun setPermissionGranted(callback: () -> Unit): PinPermission {
        PinPermissionInstance.Instance().permissionGranted = callback
        return this
    }

    fun setPermissionDenied(callback: () -> Unit): PinPermission {
        PinPermissionInstance.Instance().permissionDenied = callback
        return this
    }

    fun addPermission(permission: String): PinPermission {
        PinPermissionInstance.Instance().requestPermissions.add(permission)
        return this
    }

    fun check() {
        var permissionCheck = true
        PinPermissionInstance.Instance().requestPermissions.forEachIndexed { index, s ->
            if (permissionCheck)
                if (!checkPermission(s))
                    permissionCheck = false
        }
        if (!permissionCheck) {
            val pinPermissionIntent = Intent(context, PinPermissionActivity::class.java)
            context.startActivityForResult(pinPermissionIntent, 0)
        }
    }
}