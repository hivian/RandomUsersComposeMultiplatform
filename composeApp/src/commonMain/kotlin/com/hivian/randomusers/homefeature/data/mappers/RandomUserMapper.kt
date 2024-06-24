package com.hivian.randomusers.homefeature.data.mappers

import com.hivian.randomusers.core.datasources.models.RandomUserDTO
import com.hivian.randomusers.homefeature.domain.models.Address
import com.hivian.randomusers.homefeature.domain.models.RandomUser
import com.hivian.randomusers.core.datasources.database.RandomUserEntity

enum class ImageSize {
    THUMBNAIL,
    MEDIUM,
    LARGE
}

fun RandomUserEntity.mapToRandomUserDTO() : RandomUserDTO {
    return RandomUserDTO(
        localId = localId,
        gender = gender,
        name = name,
        location = location,
        email = email,
        phone = phone,
        cell = cell,
        picture = picture
    )
}

fun RandomUserDTO.mapToRandomUserEntity() : RandomUserEntity {
    return RandomUserEntity(
        localId = localId,
        gender = gender,
        name = name,
        location = location,
        email = email,
        phone = phone,
        cell = cell,
        picture = picture
    )
}

fun List<RandomUserEntity>.mapToRandomUsersDTO() : List<RandomUserDTO> {
    return map { it.mapToRandomUserDTO() }
}

fun RandomUserDTO.mapToRandomUser(imageSize: ImageSize) : RandomUser {
    return RandomUser(
        id = localId,
        gender = gender,
        firstName = name.first,
        lastName = name.last,
        email = email,
        phone = phone,
        cell = cell,
        picture = when (imageSize) {
            ImageSize.THUMBNAIL -> picture.thumbnail
            ImageSize.MEDIUM -> picture.medium
            ImageSize.LARGE -> picture.large
        },
        address = Address(
            city = location.city,
            state = location.state,
            country = location.country,
            latitude = location.coordinates.latitude,
            longitude = location.coordinates.longitude,
        )
    )
}

fun List<RandomUserDTO>.mapToRandomUsers(imageSize: ImageSize): List<RandomUser> {
    return map { it.mapToRandomUser(imageSize) }
}
