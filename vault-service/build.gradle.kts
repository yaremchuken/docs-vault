plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.openapi.generator") version "7.10.0"
	kotlin("plugin.jpa") version "1.9.25"
	kotlin("kapt") version "2.1.0"
}

group = "exp.yaremchuken"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.3")

	implementation("org.openapitools:openapi-generator-gradle-plugin:7.10.0")

	implementation("org.springdoc:springdoc-openapi-ui:1.8.0")
	implementation("io.swagger.core.v3:swagger-annotations:2.2.28")
	implementation("jakarta.validation:jakarta.validation-api")
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("com.google.guava:guava:33.4.0-jre")

	// MapStruct
	implementation("org.mapstruct:mapstruct:1.6.3")
	kapt("org.mapstruct:mapstruct-processor:1.6.3")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	runtimeOnly("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

openApiGenerate {
	generatorName.set("spring")
	inputSpec.set("$rootDir/src/main/resources/openapi/api/api.yaml")
	outputDir.set("$buildDir/generated")
	ignoreFileOverride.set(".openapi-generator-ignore")
	apiPackage.set("exp.yaremchuken.vault_service.openapi.api")
	invokerPackage.set("exp.yaremchuken.vault_service.openapi.invoker")
	modelPackage.set("exp.yaremchuken.vault_service.openapi.model")
	configOptions.put("java8", "true")
	configOptions.put("library", "spring-boot")
	configOptions.put("dateLibrary", "java8")
	configOptions.put("useJakartaEe", "true")
	configOptions.put("useTags", "true")
	configOptions.put("interfaceOnly", "true")
	configOptions.put("reactive", "false")
	configOptions.put("useBeanValidation", "true")
	configOptions.put("performBeanValidation", "true")
	configOptions.put("useOptional", "false")
	configOptions.put("serviceInterface", "true")
	configOptions.put("serviceImplementation", "false")
}

afterEvaluate {
	tasks.all {
		if (this is org.jetbrains.kotlin.gradle.tasks.KotlinCompile) {
			dependsOn(tasks.named("openApiGenerate"))
		}
	}
}

sourceSets {
	main {
		java {
			srcDirs(file("$buildDir/generated/src/main/java"))
		}
	}
}
