package com.skuknuraknu.myexpense.repository

import com.skuknuraknu.myexpense.entity.Role
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Optional<Role>

    fun findByNameIn(names: Collection<String>): List<Role>
}
