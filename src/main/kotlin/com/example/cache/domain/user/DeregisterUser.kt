package com.example.cache.domain.user

import com.example.cache.domain.user.model.UserCommand

interface DeregisterUser {
    fun deregister(command: UserCommand.Deregister)
}
