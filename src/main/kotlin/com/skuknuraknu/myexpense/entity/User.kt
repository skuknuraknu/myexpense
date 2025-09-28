package com.skuknuraknu.myexpense.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime
import java.time.Instant;
import jakarta.persistence.FetchType


@Entity
@Table(name = "tb_users")
class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    var id: Long? = 0

    @Column(name="username", nullable=false, unique=true)
    var username: String? = null

    @Column(name="password", nullable=false)
    var password: String? = null

    @Column(name = "created_at", nullable = false)
    var createdAt: Instant? = Instant.now()

    @Column(name = "updated_at")
    var updatedAt: Instant? = Instant.now()

    @OneToMany(
        mappedBy = "user",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var userRoles: MutableSet<UserRole> = mutableSetOf()
}
