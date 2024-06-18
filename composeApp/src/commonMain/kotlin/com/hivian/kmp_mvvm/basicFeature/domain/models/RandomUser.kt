package com.hivian.kmp_mvvm.basicFeature.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Address (

    val city: String,

    val state: String,

    val country: String,

    val latitude: Double,

    val longitude: Double

)

@Serializable
data class RandomUser(

    val id: Int,

    val gender: String,

    val firstName: String,

    val lastName: String,

    val email: String,

    val phone: String,

    val cell: String,

    val picture: String,

    val address: Address

) {

    val fullName : String
        get() = "$firstName $lastName"

}
