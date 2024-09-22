package com.example.cache.domain.user

import com.example.cache.domain.user.model.User
import com.example.cache.domain.user.model.UserCommand

interface RegisterUser {
    fun register(command: UserCommand.Register): User
}
