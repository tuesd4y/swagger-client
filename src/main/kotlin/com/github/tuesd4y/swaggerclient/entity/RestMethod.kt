package com.github.tuesd4y.swaggerclient.entity

import javax.json.Json
import javax.json.JsonObject

/**
 * @author Christopher Stelzmüller
 * @version 1.0
 * created on 13.12.2016
 */

class RestMethod(val path: String, val method: String, val originalObject: JsonObject) {
    constructor() : this("", "", Json.createObjectBuilder().build())

    override fun toString() = String.format("%0$-10s %1s", method+":", path)
}