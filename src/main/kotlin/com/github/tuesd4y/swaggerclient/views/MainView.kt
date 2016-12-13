package com.github.tuesd4y.swaggerclient.views

import com.github.tuesd4y.swaggerclient.controllers.SwaggerController
import com.github.tuesd4y.swaggerclient.entity.RestMethod
import com.github.tuesd4y.swaggerclient.entity.RestResource
import com.github.tuesd4y.swaggerclient.entity.SwaggerConfig
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import tornadofx.View
import tornadofx.bind
import tornadofx.onChange

/**
 * @author Christopher Stelzmüller
 * @version 1.0
 * created on 13.12.2016
 */

class MainView : View() {
    override val root: VBox by fxml("/views/MainView.fxml")

    val tf_baseUrl: TextField by fxid()
    val lbl_swaggerVersion: Label by fxid()
    val lv_resources: ListView<RestResource> by fxid()
    val lv_methods: ListView<RestMethod> by fxid()

    val swaggerController: SwaggerController by inject()
    val swaggerConfig: SwaggerConfig = SwaggerConfig()

    fun connect() {
        swaggerConfig.jsonObject = swaggerController.loadSwaggerFile(tf_baseUrl.text)
    }

    init {
        tf_baseUrl.text = "http://10.0.0.254:8080/werhats"
        lbl_swaggerVersion.bind(swaggerConfig.swaggerVersionProperty)
        lv_resources.items = swaggerConfig.restResources
        lv_methods.items = swaggerConfig.restMethods
        lv_resources.selectionModel.selectedItemProperty().onChange {ob -> swaggerConfig._selectedResource = ob as RestResource }

    }
}
