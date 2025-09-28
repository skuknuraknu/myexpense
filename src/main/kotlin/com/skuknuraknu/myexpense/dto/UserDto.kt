package com.skuknuraknu.myexpense.dto

import java.time.Instant

data class UserDto(
    val id: Long?,
    val username: String,
    val createdAt: Instant?,
    val updatedAt: Instant?,
    val roles: List<RoleDto>
)
