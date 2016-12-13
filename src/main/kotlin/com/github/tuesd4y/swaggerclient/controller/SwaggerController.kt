package com.github.tuesd4y.swaggerclient.controller

import com.github.tuesd4y.swaggerclient.entity.RestMethod
import com.github.tuesd4y.swaggerclient.entity.RestResource
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

    fun getDataFromJson(jsonObject: JsonObject): Set<RestResource> {
        val resourcesObject = jsonObject.getJsonObject("paths")
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