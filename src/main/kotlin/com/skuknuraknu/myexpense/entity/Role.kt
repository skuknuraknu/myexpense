import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime
import java.time.Instant;

@Entity
@Table(name = "tb_roles")
class Role {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    var id: Long? = 0

    @Column(name="name", nullable=false, unique=true)
    var name: String? = null

    @Column(name = "created_at", nullable = false)
    var createdAt: Instant? = Instant.now()
    
    @Column(name = "updated_at")
    var updatedAt: Instant? = Instant.now()
    
}