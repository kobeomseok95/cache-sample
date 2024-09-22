package com.example.cache.domain.user.model

import java.time.LocalDate
import java.util.UUID

interface UserQuery {
    data class Page(
        val ids: List<UUID> = listOf(),
        val emails: List<String> = listOf(),
        val names: List<String> = listOf(),
        val genders: List<User.Profile.Gender> = listOf(),
        val birthdayFrom: LocalDate? = null,
        val birthdayTo: LocalDate? = null,
        val page: Int = 0,
        val size: Int = 10,
    )
}
