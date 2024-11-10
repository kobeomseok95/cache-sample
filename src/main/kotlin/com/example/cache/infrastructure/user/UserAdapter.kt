package com.example.cache.infrastructure.user

import com.example.cache.domain.user.infrastructure.CreateUser
import com.example.cache.domain.user.infrastructure.QueryUser
import com.example.cache.domain.user.infrastructure.SaveUser
import com.example.cache.domain.user.model.User
import com.example.cache.domain.user.model.UserCommand
import com.example.cache.infrastructure.jpa.user.ProfileEntity
import com.example.cache.infrastructure.jpa.user.ProfileJpaRepository
import com.example.cache.infrastructure.jpa.user.UserEntity
import com.example.cache.infrastructure.jpa.user.UserJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
internal class UserAdapter(
    private val userJpaRepository: UserJpaRepository,
    private val profileJpaRepository: ProfileJpaRepository,
) : CreateUser, SaveUser, QueryUser {
    override fun create(command: UserCommand.Register): User {
        return UserEntity(
            id = UUID.randomUUID(),
            email = command.email,
            password = command.password,
            deleted = false,
            deletedAt = null,
        )
    }

    override fun save(user: User): User {
        val savedUser = userJpaRepository.save(UserEntity.of(user))
        val savedProfile = user.profile?.let {
            profileJpaRepository.save(ProfileEntity.of(savedUser.id, it))
        }
        return savedUser.toDomain(savedProfile)
    }

    override fun findById(id: UUID): User? {
        val user = userJpaRepository.findByIdOrNull(id)
        val profile = profileJpaRepository.findByUserId(id)
        return user?.toDomain(profile)
    }

    override fun findByEmail(email: String): User? {
        val user = userJpaRepository.findByEmail(email)
        return user?.let {
            val profile = profileJpaRepository.findByUserId(it.id)
            it.toDomain(profile)
        }
    }
}
