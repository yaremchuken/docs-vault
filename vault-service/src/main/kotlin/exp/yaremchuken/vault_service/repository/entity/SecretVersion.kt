package exp.yaremchuken.vault_service.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime

@Entity
data class SecretVersion(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @Column(name = "secret_id", nullable = false)
    val secretId: Long,

    @Column(name = "title", nullable = false)
    val title: String,

    @Column(name = "secret_value", nullable = false)
    val secretValue: String,

    @field:CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
)
