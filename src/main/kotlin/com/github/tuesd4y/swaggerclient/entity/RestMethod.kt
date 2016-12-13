package com.github.tuesd4y.swaggerclient.entity

import javax.json.JsonObject

/**
 * @author Christopher Stelzmüller
 * @version 1.0
 * created on 13.12.2016
 */

class RestMethod(val path: String, val method: String, val originalObject: JsonObject) {
}