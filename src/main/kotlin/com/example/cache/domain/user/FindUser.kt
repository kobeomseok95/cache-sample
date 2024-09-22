package com.example.cache.domain.user

import com.example.cache.domain.user.model.User
import com.example.cache.domain.user.model.UserQuery
import org.springframework.data.domain.Page
import java.util.UUID

interface FindUser {
    fun findById(id: UUID): User?
    fun findByEmail(email: String): User?
    fun findPage(query: UserQuery.Page): Page<User>
}