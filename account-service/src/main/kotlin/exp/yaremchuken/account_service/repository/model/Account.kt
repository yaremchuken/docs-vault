package exp.yaremchuken.account_service.repository.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant

@Entity
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int = 0,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column
    val username: String? = null,

    // @OneToOne(cascade = [CascadeType.ALL])
    // @JoinColumn(name = "account_credentials_id", referencedColumnName = "account_id")
    // val credentials: AccountCredentials,
    //
    // @OneToOne(cascade = [CascadeType.ALL])
    // @JoinColumn(name = "account_settings_id", referencedColumnName = "account_id")
    // val settings: AccountSettings,

    @field:CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: Instant = Instant.now(),

    @field:UpdateTimestamp
    @Column(name = "updated_at", insertable = false)
    val updatedAt: Instant = Instant.now(),

    @Column(name = "deleted_at")
    val deletedAt: Instant? = null
)
