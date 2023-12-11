package com.example.helloworld

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import androidx.car.app.CarContext
import androidx.car.app.*
import androidx.car.app.ScreenManager
import androidx.car.app.Session
import androidx.car.app.model.CarIcon
import androidx.car.app.model.GridItem
import androidx.car.app.model.GridTemplate
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Pane
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.core.graphics.drawable.IconCompat

class MainSession : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        return if (hasLocationPermission())
            //MainSession(carContext)
            GridScreen(carContext)
        else {
            //carContext.getCarService(ScreenManager::class.java).push(MainScreen(carContext))
            carContext.getCarService(ScreenManager::class.java).push(GridScreen(carContext))
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
            itemListBuilder.addItem(Row.Builder()
                .setTitle(str)
                .setOnClickListener {
                    screenManager.push(SubListScreen(
                        carContext,
                        str
                    ))
                }
                .build())

        }

        return ListTemplate.Builder()
            .setTitle("This is a list")
            .setSingleList(itemListBuilder.build())
            .build()
    }

}

class GridScreen(carContext: CarContext) : Screen(carContext) {
    override fun onGetTemplate(): Template {
        val gridBuilder = GridTemplate.Builder()
        val gridItemList = mutableListOf(
            "Grid Item 1",
            "Grid Item 2",
            "Grid Item 3",
            "Grid Item 4",
            "Grid Item 5",
            "Grid Item 6",
            "Grid Item 7",
            "Grid Item 8",
            "Grid Item 9",
            "Grid Item 10"
        )

        val gridItemListBuilder = ItemList.Builder()

        gridItemList.forEach { item ->
            val gridItemBuilder = GridItem.Builder()
                .setTitle(item)
                .setImage(
                    CarIcon.Builder(
                        IconCompat.createWithResource(
                            carContext,
                            R.drawable.baseline_device_hub_24
                        )
                    ).build()
                )
                .setOnClickListener {
                    screenManager.push(SubListScreen(
                        carContext,
                        item
                    ))
                }
                .build()
            gridItemListBuilder.addItem(gridItemBuilder)
        }

        return GridTemplate.Builder()
            .setTitle("This is a grid!")
            .setSingleList(gridItemListBuilder.build())
            .build()

    }
}