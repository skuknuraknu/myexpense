package com.skuknuraknu.myexpense.repository;

import com.skuknuraknu.myexpense.entity.User
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>

    @Query(
        "select distinct u from User u " +
            "left join fetch u.userRoles ur " +
            "left join fetch ur.role"
    )
    fun findAllWithRoles(): List<User>
}
