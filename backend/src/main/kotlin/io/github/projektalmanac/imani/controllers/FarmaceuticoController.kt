package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.FarmaceuticoApi
import io.github.projektalmanac.imani.generated.dto.NuevoFarmaceuticoDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class FarmaceuticoController : FarmaceuticoApi {
    override fun postFarmaceutico(nuevoFarmaceuticoDto: NuevoFarmaceuticoDto?): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }
}
