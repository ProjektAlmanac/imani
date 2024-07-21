package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.FarmaceuticoApi
import io.github.projektalmanac.imani.generated.dto.NuevoFarmaceuticoDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class FarmaceuticoController : FarmaceuticoApi {
    override fun postFarmaceutico(nuevoFarmaceuticoDto: NuevoFarmaceuticoDto?): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }
}
