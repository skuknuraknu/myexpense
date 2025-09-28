package com.skuknuraknu.myexpense.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime
import java.time.Instant;

@Entity
@Table(name = "tb_user_roles")
class UserRole {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    var id: Long? = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    var role: Role? = null

    @Column(name = "assigned_at", nullable = false)
    var assignedAt: Instant? = Instant.now()
}
