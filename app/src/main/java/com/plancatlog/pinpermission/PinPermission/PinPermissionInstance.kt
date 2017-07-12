package com.plancatlog.pinpermission.PinPermission

import android.content.Context

/**
 * Created by plancatlog on 2017. 7. 12..
 */

open class PinPermissionInstance {
    val requestPermissions = arrayListOf<String>()
    var permissionDenied: (() -> Unit)? = null
    var permissionGranted: (() -> Unit)? = null

    companion object {
        var instance : PinPermissionInstance? = null

        fun Instance() : PinPermissionInstance {
            if(instance == null)
                instance = PinPermissionInstance()
            return instance!!
        }
    }
}