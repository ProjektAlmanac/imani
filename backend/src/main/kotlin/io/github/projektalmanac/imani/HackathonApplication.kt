package io.github.projektalmanac.imani

import io.github.projektalmanac.imani.services.TestDataService
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ImaniApplication(val testDataService: TestDataService) {
	@PostConstruct
	fun init() {
		testDataService.generarDatos()
	}
}

fun main(args: Array<String>) {
	runApplication<ImaniApplication>(*args)
}
