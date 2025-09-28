package com.skuknuraknu.myexpense.service

import com.skuknuraknu.myexpense.dto.CreateUserRequest
import com.skuknuraknu.myexpense.dto.RoleDto
import com.skuknuraknu.myexpense.dto.UserDto
import com.skuknuraknu.myexpense.entity.Role
import com.skuknuraknu.myexpense.entity.User
import com.skuknuraknu.myexpense.entity.UserRole
import com.skuknuraknu.myexpense.repository.RoleRepository
import com.skuknuraknu.myexpense.repository.UserRepository
import java.time.Instant
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private const val USERNAME_ALREADY_EXISTS = "Username sudah digunakan"
private const val ROLE_NOT_FOUND = "Role tidak ditemukan"

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository
) {

    @Transactional(readOnly = true)
    fun getAllUsers(): List<UserDto> =
        userRepository.findAllWithRoles().map { it.toDto() }

    @Transactional
    fun createUser(createUserRequest: CreateUserRequest): UserDto {
        val normalizedUsername = createUserRequest.username.trim()

        userRepository.findByUsername(normalizedUsername)
            .ifPresent { throw IllegalArgumentException(USERNAME_ALREADY_EXISTS) }

        val roles = resolveRoles(createUserRequest.roles)

        val now = Instant.now()
        val user = User().apply {
            username = normalizedUsername
            password = createUserRequest.password
            createdAt = now
            updatedAt = now
        }

        roles.forEach { role ->
            user.userRoles += UserRole().apply {
                this.user = user
                this.role = role
            }
        }

        return userRepository.save(user).toDto()
    }

    private fun User.toDto() = UserDto(
        id = id,
        username = username.orEmpty(),
        createdAt = createdAt,
        updatedAt = updatedAt,
        roles = userRoles
            .mapNotNull { userRole ->
                userRole.role?.let { role ->
                    RoleDto(
                        id = role.id,
                        name = role.name.orEmpty()
                    )
                }
            }
            .sortedBy(RoleDto::name)
    )

    private fun resolveRoles(roleNames: Set<String>): List<Role> {
        val normalizedNames = roleNames
            .map(String::trim)
            .filter(String::isNotBlank)
            .toSet()

        if (normalizedNames.isEmpty()) {
            return emptyList()
        }

        val roles = roleRepository.findByNameIn(normalizedNames)
        val foundNames = roles.mapNotNull { it.name?.lowercase() }.toSet()
        val missingRoles = normalizedNames.filter { it.lowercase() !in foundNames }

        if (missingRoles.isNotEmpty()) {
            throw IllegalArgumentException("$ROLE_NOT_FOUND: ${missingRoles.joinToString(", ")}")
        }

        return roles
    }
}
