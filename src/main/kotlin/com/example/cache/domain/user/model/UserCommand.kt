package com.example.cache.domain.user.model

import java.util.UUID

interface UserCommand {
    data class Register(
        val id: UUID,
        val email: String,
        val password: String,
    ) : UserCommand

    data class Update(
        val id: UUID,
        val email: String,
        val password: String,
        val profile: User.Profile,
    )

    data class Deregister(
        val id: UUID,
    )
}
