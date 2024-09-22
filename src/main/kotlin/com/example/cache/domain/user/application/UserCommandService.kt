package com.example.cache.domain.user.application

import com.example.cache.domain.user.DeregisterUser
import com.example.cache.domain.user.RegisterUser
import com.example.cache.domain.user.UpdateUser
import com.example.cache.domain.user.infrastructure.CreateUser
import com.example.cache.domain.user.infrastructure.QueryUser
import com.example.cache.domain.user.infrastructure.SaveUser
import com.example.cache.domain.user.model.User
import com.example.cache.domain.user.model.UserCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
internal class UserCommandService(
    private val createUser: CreateUser,
    private val saveUser: SaveUser,
    private val queryUser: QueryUser,
) : RegisterUser, UpdateUser, DeregisterUser {

    override fun register(command: UserCommand.Register): User {
        val created = createUser.create(command)
        return saveUser.save(created)
    }

    override fun update(command: UserCommand.Update): User {
        val user = findById(command.id)
        val updated = user.update(command)
        return saveUser.save(updated)
    }

    private fun findById(id: UUID): User {
        return queryUser.findById(id) ?: throw IllegalArgumentException("User not found")
    }

    override fun deregister(command: UserCommand.Deregister) {
        val user = findById(command.id)
        val deregistered = user.deregister()
        saveUser.save(deregistered)
    }
}
