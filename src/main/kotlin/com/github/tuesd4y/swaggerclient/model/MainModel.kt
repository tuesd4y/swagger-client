package com.github.tuesd4y.swaggerclient.model

import com.github.tuesd4y.swaggerclient.controller.SwaggerController
import com.github.tuesd4y.swaggerclient.entity.RestMethod
import com.github.tuesd4y.swaggerclient.entity.RestResource
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.ViewModel
import tornadofx.getProperty
import tornadofx.onChange
import tornadofx.property
import javax.json.Json
import javax.json.JsonObject

/**
 * @author Christopher Stelzmüller
 * @version 1.0
 * created on 13.12.2016
 */

class MainModel : ViewModel(){
    val swaggerController: SwaggerController by inject()

    private var _jsonObject: JsonObject = Json.createObjectBuilder().build()

    var jsonObject = _jsonObject
        set(value) {
            _jsonObject = value
            _swaggerVersion = _jsonObject.getString("swagger","")
            restResources.setAll(swaggerController.getDataFromJson(_jsonObject))
        }
        get

    var _swaggerVersion: String by property("")
    val swaggerVersionProperty = getProperty(MainModel::_swaggerVersion)

    var _selectedResource: RestResource by property(RestResource(""))
    val selectedResourceProperty = getProperty(MainModel::_selectedResource).onChange {
            restMethods.setAll(it!!.methods)
        }

    var _selectedMethod: RestMethod by property(RestMethod())
    val selectedMethodProperty = getProperty(MainModel::_selectedMethod)

    val restResources: ObservableList<RestResource> = FXCollections.observableArrayList<RestResource>()
    val restMethods: ObservableList<RestMethod> = FXCollections.observableArrayList<RestMethod>()

    var _isFullscreen: Boolean by property(false)
    val fullScreenProperty = getProperty(MainModel::_isFullscreen)

}

