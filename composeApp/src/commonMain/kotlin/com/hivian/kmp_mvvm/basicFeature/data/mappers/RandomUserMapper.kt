package com.hivian.kmp_mvvm.basicFeature.data.mappers

import com.hivian.kmp_mvvm.basicFeature.domain.models.Address
import com.hivian.kmp_mvvm.basicFeature.domain.models.RandomUser

enum class ImageSize {
    THUMBNAIL,
    MEDIUM,
    LARGE
}

fun com.hivian.kmp_mvvm.core.datasources.models.RandomUserDTO.mapToRandomUser(imageSize: ImageSize) : RandomUser {
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

fun List<com.hivian.kmp_mvvm.core.datasources.models.RandomUserDTO>.mapToRandomUsers(imageSize: ImageSize): List<RandomUser> {
    return map { it.mapToRandomUser(imageSize) }
}
