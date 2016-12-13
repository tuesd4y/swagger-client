package com.github.tuesd4y.swaggerclient.entity

import javafx.beans.property.ObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.getProperty
import tornadofx.property
import javax.json.Json
import javax.json.JsonObject

/**
 * @author Christopher Stelzmüller
 * @version 1.0
 * created on 13.12.2016
 */

class SwaggerConfig {
    private var _jsonObject: JsonObject = Json.createObjectBuilder().build()

    var jsonObject = _jsonObject
        set(value) {
            _jsonObject = value
            _swaggerVersion = _jsonObject.getString("swagger","")
            restResources.setAll(getDataFromJson())
        }
        get

    var _swaggerVersion by property("")
    val swaggerVersionProperty: ObjectProperty<String>
            get() = getProperty(SwaggerConfig::_swaggerVersion)


    val restResources: ObservableList<RestResource> = FXCollections.observableArrayList<RestResource>()


    private fun getDataFromJson(): Set<RestResource> {
        val resourcesObject = _jsonObject.getJsonObject("paths")
        val resourcesSet = mutableSetOf<RestResource>()
        resourcesObject.forEach { pathValue ->
            val (path, methodObject) = pathValue

            val p = if(path.lastIndexOf("/") > 0) {
                path.subSequence(1,path.indexOf("/", 1)).toString()
            } else path.substring(1)

            val res = RestResource(p)

            (methodObject as JsonObject).forEach { obj ->
                res += RestMethod(path, obj.key, obj.value as JsonObject)
            }

            val r = resourcesSet.firstOrNull { it.path == res.path }
            if(r != null) {
                r += res
            } else {
                resourcesSet += res
            }
        }

        return resourcesSet
    }
}

