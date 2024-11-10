package com.example.cache.infrastructure.cache.user

import com.example.cache.domain.user.model.AbstractUser
import com.example.cache.domain.user.model.User
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.time.LocalDate
import java.util.UUID

@RedisHash("users")
data class UserRedisEntity(
    @Id
    override val id: UUID,
    override var email: String,
    override var password: String,
    override var deleted: Boolean,
    override var deletedAt: LocalDate?,
    override var profile: User.Profile?
) : AbstractUser() {
    fun toDomain(): User {
        return this
    }
}
