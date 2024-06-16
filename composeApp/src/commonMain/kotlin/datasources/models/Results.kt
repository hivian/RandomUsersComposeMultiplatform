package com.hivian.kmp_mvvm.datasources.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import datasources.models.Location

data class Results(

    val results: List<RandomUserDTO>
)

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
