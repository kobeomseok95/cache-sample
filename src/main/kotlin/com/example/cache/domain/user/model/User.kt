package com.example.cache.domain.user.model

import java.time.LocalDate
import java.util.UUID

interface User {
    val id: UUID
    val email: String
    val password: String
    val deleted: Boolean
    val deletedAt: LocalDate?
    val profile: Profile?

    fun update(command: UserCommand.Update) : User

    fun deregister(): User

    data class Profile(
        val name: String,
        val gender: Gender,
        val address: String,
        val phone: String,
        val birthday: LocalDate,
    ) {
        enum class Gender {
            MALE,
            FEMALE,
            ;
        }
    }
}

abstract class AbstractUser : User {
    abstract override var email: String
        protected set
    abstract override var password: String
        protected set
    abstract override var deleted: Boolean
        protected set
    abstract override var deletedAt: LocalDate?
        protected set
    abstract override var profile: User.Profile?
        protected set

    override fun update(command: UserCommand.Update): User {
        this.email = command.email
        this.password = command.password
        this.profile = command.profile
        return this
    }

    override fun deregister(): User {
        this.deleted = true
        this.deletedAt = LocalDate.now()
        return this
    }
}
