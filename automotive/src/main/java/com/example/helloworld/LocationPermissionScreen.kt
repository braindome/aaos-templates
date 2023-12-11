package com.example.helloworld

import android.Manifest.permission
import android.annotation.SuppressLint
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.*
import androidx.car.app.model.Action.FLAG_PRIMARY

class LocationPermissionScreen(carContext: CarContext) : Screen(carContext) {

    @SuppressLint("UnsafeOptInUsageError")
    override fun onGetTemplate(): Template {
        val permissions: MutableList<String> = ArrayList()
        permissions.add(permission.ACCESS_FINE_LOCATION)

        val grantAction = Action.Builder()
            .setTitle(carContext.getString(R.string.ok))
            .setFlags(FLAG_PRIMARY)
            .setOnClickListener(
                ParkedOnlyOnClickListener.create {
                    carContext.requestPermissions(permissions) { _, _ ->
                        finish()
                    }
                }
            )
            .build()

        val skipAction = Action.Builder()
            .setTitle(carContext.getString(R.string.skip))
            .setBackgroundColor(CarColor.SECONDARY)
            .setOnClickListener { finish() }
            .build()

        return MessageTemplate
            .Builder(carContext.getString(R.string.location_permission_message))
            .addAction(grantAction)
            .addAction(skipAction)
            .setTitle(carContext.getString(R.string.app_name))
            .setHeaderAction(Action.APP_ICON)
            .build()
    }
}