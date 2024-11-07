package com.example.cache.api

import com.example.cache.domain.user.DeregisterUser
import com.example.cache.domain.user.FindUser
import com.example.cache.domain.user.RegisterUser
import com.example.cache.domain.user.UpdateUser
import com.example.cache.domain.user.model.UserCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class UserApi(
    private val registerUser: RegisterUser,
    private val updateUser: UpdateUser,
    private val deregisterUser: DeregisterUser,
    private val findUser: FindUser,
) {
    @PostMapping("/users")
    fun register(command: UserCommand.Register): ResponseEntity<Unit> {
        registerUser.register(command)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/users/{id}")
    fun update(
        @PathVariable id: UUID,
        command: UserCommand.Update,
    ): ResponseEntity<Unit> {
        updateUser.update(command)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/users/{id}")
    fun deregister(
        @PathVariable id: UUID,
    ): ResponseEntity<Unit> {
        deregisterUser.deregister(UserCommand.Deregister(id))
        return ResponseEntity.ok().build()
    }

    @GetMapping("/users/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<UserResponse> {
        return findUser.findById(id)
            ?.let { UserResponse.of(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/users/email/{email}")
    fun findByEmail(@PathVariable email: String): ResponseEntity<UserResponse> {
        return findUser.findByEmail(email)
            ?.let { UserResponse.of(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
    }
}
