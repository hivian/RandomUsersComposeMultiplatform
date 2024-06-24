package com.hivian.randomusers.core.datasources.models

import kotlinx.serialization.Serializable

@Serializable
data class Results(

    val results: List<RandomUserDTO>
)

@Serializable
data class RandomUserDTO(

    var localId: Int = 0,

    val gender: String,

    val name: Name,

    val location: Location,

    val email: String,

    val phone: String,

    val cell: String,

    val picture: Picture

)
