package com.hivian.randomusers.core.datasources.models

import kotlinx.serialization.Serializable

@Serializable
data class Picture(

    val large: String,

    val medium: String,

    val thumbnail: String

) {

    companion object {
        val EMPTY = Picture(large = "", medium = "", thumbnail = "")
    }

}
