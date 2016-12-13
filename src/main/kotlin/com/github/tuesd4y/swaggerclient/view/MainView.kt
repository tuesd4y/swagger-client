package com.github.tuesd4y.swaggerclient.view

import com.github.tuesd4y.swaggerclient.entity.RestMethod
import com.github.tuesd4y.swaggerclient.entity.RestResource
import com.github.tuesd4y.swaggerclient.model.MainModel
import javafx.application.Platform
import javafx.scene.control.ListView
import javafx.scene.control.MenuItem
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.VBox
import tornadofx.FX
import tornadofx.View
import tornadofx.onChange

/**
 * @author Christopher Stelzmüller
 * @version 1.0
 * created on 13.12.2016
 */

class MainView : View() {
    override val root: VBox by fxml("/views/MainView.fxml")

    val tf_baseUrl: TextField by fxid()
    val lv_resources: ListView<RestResource> by fxid()
    val lv_methods: ListView<RestMethod> by fxid()

    val mi_close: MenuItem by fxid()
    val mi_settings: MenuItem by fxid()
    val mi_info: MenuItem by fxid()
    val mi_fullscreen: MenuItem by fxid()

    val mainModel: MainModel by inject()

    fun connect() {
        mainModel.jsonObject = mainModel.swaggerController.loadSwaggerFile(tf_baseUrl.text)
    }

    fun close() {
        Platform.exit()
    }

    fun info() {
        //TODO: show information from MainModel in popup
    }

    fun settings() {
        //TODO: open a popup window
    }

    fun fullscreen() {
        mainModel._isFullscreen = !mainModel._isFullscreen
    }

    init {
        mainModel.fullScreenProperty.onChange {
            FX.primaryStage.isFullScreen = it!!
        }

        mi_close.accelerator = KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN)
        mi_info.accelerator = KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN)
        mi_settings.accelerator = KeyCodeCombination(KeyCode.COMMA, KeyCombination.CONTROL_DOWN)
        mi_fullscreen.accelerator = KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN)

        tf_baseUrl.text = "http://10.0.0.254:8080/werhats"
        lv_resources.items = mainModel.restResources
        lv_methods.items = mainModel.restMethods
        lv_resources.selectionModel.selectedItemProperty().onChange {ob -> mainModel._selectedResource = ob as RestResource }
        lv_methods.selectionModel.selectedItemProperty().onChange {ob -> mainModel._selectedMethod = ob as RestMethod }

    }
}
