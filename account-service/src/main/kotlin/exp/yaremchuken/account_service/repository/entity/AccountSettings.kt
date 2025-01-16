package exp.yaremchuken.account_service.repository.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Locale

@Entity
@Table(name = "account_settings")
data class AccountSettings(
    @Id
    @Column(name = "account_id", nullable = false, unique = true)
    val accountId: Int = 0,

    @Column(nullable = false)
    val locale: Locale
)
