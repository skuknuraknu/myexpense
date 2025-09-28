package com.skuknuraknu.myexpense.controller

import com.skuknuraknu.myexpense.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import jakarta.validation.Valid
import com.skuknuraknu.myexpense.dto.CreateUserRequest

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<Map<String, Any>> =
        runCatching { userService.getAllUsers() }
            .fold(
                onSuccess = { users ->
                    ResponseEntity.ok(
                        mapOf(
                            "success" to true,
                            "message" to "Berhasil mendapatkan data user",
                            "data" to users
                        )
                    )
                },
                onFailure = { ex ->
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(
                            mapOf(
                                "success" to false,
                                "message" to (ex.message ?: "Gagal mendapatkan data user")
                            )
                        )
                }
            )
    
    @PostMapping
    fun createUser(@Valid @RequestBody createUserRequest: CreateUserRequest): ResponseEntity<Map<String, Any>> =
        runCatching { userService.createUser(createUserRequest) }
            .fold(
                onSuccess = { user ->
                    ResponseEntity.status(HttpStatus.CREATED).body(
                        mapOf(
                            "success" to true,
                            "message" to "Berhasil menambahkan user",
                            "data" to user
                        )
                    )
                },
                onFailure = { ex ->
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(
                            mapOf(
                                "success" to false,
                                "message" to (ex.message ?: "Gagal menambahkan user")
                            )
                        )
                }
            )
}
