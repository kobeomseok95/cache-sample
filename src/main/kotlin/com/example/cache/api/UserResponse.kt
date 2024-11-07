package com.example.cache.api

import com.example.cache.domain.user.model.User
import java.time.LocalDate
import java.util.UUID

data class UserResponse(
    val id: UUID,
    val email: String,
    val password: String,
    val deleted: Boolean,
    val deletedAt: LocalDate?,
    val profile: User.Profile?,
) {
    companion object {
        fun of(user: User): UserResponse {
            return with(user) {
                UserResponse(
                    id = id,
                    email = email,
                    password = password,
                    deleted = deleted,
                    deletedAt = deletedAt,
                    profile = profile,
                )
            }
        }
    }
}
