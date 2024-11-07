package com.example.cache.infrastructure.jpa.user

import com.example.cache.domain.user.model.AbstractUser
import com.example.cache.domain.user.model.Profile
import com.example.cache.domain.user.model.User
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override val id: UUID,
    override var email: String,
    override var password: String,
    override var deleted: Boolean,
    override var deletedAt: LocalDate?,
    @Transient override var profile: User.Profile? = null
) : AbstractUser() {
    fun toDomain(profile: ProfileEntity?): User {
        return this.apply {
            this.profile = profile
        }
    }

    companion object {
        fun of(user: User): UserEntity {
            return UserEntity(
                id = user.id,
                email = user.email,
                password = user.password,
                deleted = user.deleted,
                deletedAt = user.deletedAt,
            )
        }
    }

}

@Entity
@Table(name = "user_profiles")
class ProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),
    val userId: UUID,
    override var name: String,
    override var gender: User.Profile.Gender,
    override var address: String,
    override var phone: String,
    override var birthday: LocalDate,
) : User.Profile {
    fun toDomain(): Profile {
        return Profile(
            name = name,
            gender = gender,
            address = address,
            phone = phone,
            birthday = birthday,
        )
    }

    companion object {
        fun of(userId: UUID, profile: User.Profile): ProfileEntity {
            return ProfileEntity(
                userId = userId,
                name = profile.name,
                gender = profile.gender,
                address = profile.address,
                phone = profile.phone,
                birthday = profile.birthday,
            )
        }
    }
}
