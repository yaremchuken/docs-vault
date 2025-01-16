package exp.yaremchuken.account_service.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "account_credentials")
data class AccountCredentials(
    @Id
    @Column(name = "account_id", nullable = false, unique = true)
    val accountId: Int = 0,

    @Column(nullable = false)
    val password: String,

    @Column
    val pinCode: String? = null,
)
