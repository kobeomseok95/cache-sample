package com.example.cache.domain.user

import com.example.cache.domain.user.model.User
import java.util.UUID

interface FindUser {
    fun findById(id: UUID): User?
    fun findByEmail(email: String): User?
}
