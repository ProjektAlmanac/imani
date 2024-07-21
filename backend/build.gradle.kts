plugins {
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
	id("org.openapi.generator") version "7.7.0"
	kotlin("plugin.jpa") version "1.9.24"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	kotlin("kapt") version "1.8.22"
}

group = "com.github.projektalmanac"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	val mapStructVersion = "1.5.5.Final"

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.14")
	implementation("jakarta.validation:jakarta.validation-api:2.0.2")
	implementation("org.mapstruct:mapstruct:$mapStructVersion")
	implementation("javax.servlet:javax.servlet-api:3.1.0")

	kapt("org.mapstruct:mapstruct-processor:$mapStructVersion")

	runtimeOnly("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

openApiGenerate {
	generatorName = "kotlin-spring"
	inputSpec = "$rootDir/../api-docs.yml"
	outputDir = "$rootDir"
	groupId = "$group"
	modelNameSuffix = "Dto"

	packageName = "io.github.projektalmanac.imani.generated"
	apiPackage = "io.github.projektalmanac.imani.generated.api"
	modelPackage = "io.github.projektalmanac.imani.generated.dto"

	additionalProperties.put("interfaceOnly", true)
	additionalProperties.put("skipDefaultInterface", true)
	additionalProperties.put("configPackage", "io.github.projektalmanac.imani.generated.config")
	additionalProperties.put("useTags", true)
	additionalProperties.put("hideGenerationTimestamp", true)
}
