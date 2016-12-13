package com.github.tuesd4y.swaggerclient.entity

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javax.json.Json
import javax.json.JsonObject

/**
 * @author Christopher Stelzmüller
 * @version 1.0
 * created on 13.12.2016
 */

class SwaggerConfig(){
    private var _jsonObject: JsonObject = Json.createObjectBuilder().build()

    var jsonObject = _jsonObject
        set(value) {
            _jsonObject = value
            swaggerVersion.set(_jsonObject.getString("swagger",""))
            restResources.setAll()
        }
        get

    val swaggerVersion: StringProperty
        get() = SimpleStringProperty("")

    val restResources: ObservableList<RestResource>
        get() = FXCollections.observableArrayList()
}