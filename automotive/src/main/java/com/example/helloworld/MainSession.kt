package com.example.helloworld

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.ScreenManager
import androidx.car.app.Session
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template

class MainSession : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        return if (hasLocationPermission())
            MainScreen(carContext)
        else {
            carContext.getCarService(ScreenManager::class.java).push(MainScreen(carContext))
            LocationPermissionScreen(carContext)
        }
    }

    private fun hasLocationPermission() = (carContext.checkSelfPermission(ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED)
}

class MainScreen(carContext: CarContext) : Screen(carContext) {
    override fun onGetTemplate(): Template {
        val itemListBuilder = ItemList.Builder()
        val categoryBox = mutableListOf<String>("Row 1", "Row 2", "Row 3", "Row 4")

        categoryBox.forEach { str ->
            itemListBuilder.addItem(Row.Builder().setTitle(str).build())

        }

        return ListTemplate.Builder()
            .setTitle("This is a list")
            .setSingleList(itemListBuilder.build())
            .build()
    }



}