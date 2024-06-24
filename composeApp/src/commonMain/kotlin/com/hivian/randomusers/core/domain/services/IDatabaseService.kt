package com.hivian.randomusers.core.domain.services

import com.hivian.randomusers.core.datasources.models.Name
import com.hivian.randomusers.core.datasources.models.RandomUserDTO

interface IDatabaseService {

    fun getUserById(userId: Int): RandomUserDTO

    fun getUserByName(name: Name): RandomUserDTO?

    fun getAllUsers(pageIndex: Int, pageSize: Int): List<RandomUserDTO>

    fun upsertUsers(users : List<RandomUserDTO>)

    fun deleteAll()

}
