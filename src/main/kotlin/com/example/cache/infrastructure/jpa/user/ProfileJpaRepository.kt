package com.example.cache.infrastructure.jpa.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

internal interface ProfileJpaRepository : JpaRepository<ProfileEntity, UUID> {
    fun findByUserId(userId: UUID): ProfileEntity?
}
