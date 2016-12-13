package com.github.tuesd4y.swaggerclient.entity

/**
 * @author Christopher Stelzmüller
 * @version 1.0
 * created on 13.12.2016
 */

class RestResource(val path: String) {
    val methods = mutableListOf<RestMethod>()

    operator fun plusAssign(method: RestMethod) {
        methods += method
    }

    operator fun plusAssign(resource: RestResource) {
        if(this.path == resource.path) {
            methods += resource.methods
        }
        else {
            throw Exception()
        }
    }

    override fun toString(): String {
        return path
    }
}