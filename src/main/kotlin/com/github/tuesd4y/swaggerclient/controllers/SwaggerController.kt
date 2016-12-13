package com.github.tuesd4y.swaggerclient.controllers

import tornadofx.Controller
import tornadofx.Rest
import javax.json.JsonObject

/**
 * @author Christopher Stelzmüller
 * @version 1.0
 * created on 13.12.2016
 */

class SwaggerController : Controller() {
    val api : Rest by inject()

    fun loadSwaggerFile(serverPath: String): JsonObject {
        //TODO: check and maybe modify server path
        return with(api) {
            baseURI = serverPath
            api.get("/rs/swagger.json").one()
        }
    }
}