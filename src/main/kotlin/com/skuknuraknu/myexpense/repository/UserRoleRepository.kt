package com.skuknuraknu.myexpense.repository

import com.skuknuraknu.myexpense.entity.UserRole
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository

interface UserRoleRepository : JpaRepository<UserRole, Long> {
}