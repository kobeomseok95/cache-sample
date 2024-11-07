package com.example.cache.infrastructure.jpa.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

internal interface UserJpaRepository : JpaRepository<UserEntity, UUID> {
    fun findByEmail(email: String): UserEntity?
}
