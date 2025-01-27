package exp.yaremchuken.account_service

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
// @OpenAPIDefinition(
// 	info = Info(
// 		title = "Accounts Control Center",
// 		version = "1.0",
// 		description = "Accounts microservice REST API",
// 		license = License(
// 			name = "Apache 2.0",
// 			url = "https://www.apache.org/licenses/LICENSE-2.0.html"
// 		)
// 	)
// )
class AccountServiceApplication

fun main(args: Array<String>) {
	runApplication<AccountServiceApplication>(*args)
}
