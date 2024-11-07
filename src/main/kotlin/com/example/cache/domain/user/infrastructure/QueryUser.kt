package com.example.cache.domain.user.infrastructure

import com.example.cache.domain.user.model.User
import java.util.UUID

interface QueryUser {
    fun findById(id: UUID): User?
    fun findByEmail(email: String): User?
}
