package com.example.helloworld

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template

class SubListScreen(
    carContext: CarContext,
    private val title: String
) : Screen(carContext){
    override fun onGetTemplate(): Template {


        return PaneTemplate.Builder(Pane.Builder()
            .addRow(
                Row.Builder()
                    .setTitle(title)
                    .build()
            )
            .build()
        )
            .setTitle("Back")
            .setHeaderAction(Action.BACK)
            .build()

    }
}