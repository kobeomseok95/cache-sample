package com.example.cache.domain.user.infrastructure

import com.example.cache.domain.user.model.User

interface SaveUser {
    fun save(user: User): User
}
