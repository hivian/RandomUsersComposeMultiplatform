package com.hivian.kmp_mvvm.datasources.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import datasources.models.Location
import datasources.models.Name
import datasources.models.Picture
import kotlinx.serialization.Serializable

@Serializable
data class Results(

    val results: List<RandomUserDTO>
)

@Serializable
@Entity(tableName = "random_user_entity")
data class RandomUserDTO(

    @PrimaryKey(autoGenerate = true)
    var localId: Int,

    val gender: String,

    val name: Name,

    val location: Location,

    val email: String,

    val phone: String,

    val cell: String,

    val picture: Picture

)
