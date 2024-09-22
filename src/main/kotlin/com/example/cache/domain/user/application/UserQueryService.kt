package com.example.cache.domain.user.application

import com.example.cache.domain.user.FindUser
import com.example.cache.domain.user.infrastructure.QueryUser
import com.example.cache.domain.user.model.User
import com.example.cache.domain.user.model.UserQuery
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException
import java.util.UUID

@Service
@Transactional(readOnly = true)
internal class UserQueryService(
    private val queryUser: QueryUser,
) : FindUser {
    override fun findById(id: UUID): User? {
        return queryUser.findById(id) ?: throw IllegalArgumentException("User not found")
    }

    override fun findByEmail(email: String): User? {
        return queryUser.findByEmail(email) ?: throw IllegalArgumentException("User not found")
    }

    override fun findPage(query: UserQuery.Page): Page<User> {
        return queryUser.findPage(query)
    }
}
