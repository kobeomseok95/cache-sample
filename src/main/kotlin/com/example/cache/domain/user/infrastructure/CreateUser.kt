package com.example.cache.domain.user.infrastructure

import com.example.cache.domain.user.model.User
import com.example.cache.domain.user.model.UserCommand

interface CreateUser {
    fun create(command: UserCommand.Register): User
}
