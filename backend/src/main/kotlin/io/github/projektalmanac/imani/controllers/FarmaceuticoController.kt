package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.FarmaceuticoApi
import io.github.projektalmanac.imani.generated.dto.FarmaceuticoDto
import io.github.projektalmanac.imani.generated.dto.NuevoFarmaceuticoDto
import io.github.projektalmanac.imani.services.FarmaceuticoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class FarmaceuticoController(private val farmaceuticoService: FarmaceuticoService) :
        FarmaceuticoApi {
    override fun postFarmaceutico(
            nuevoFarmaceuticoDto: NuevoFarmaceuticoDto?
    ): ResponseEntity<Unit> {

        farmaceuticoService.addFarmaceutico(nuevoFarmaceuticoDto)

        return ResponseEntity.noContent().build()
    }

    override fun getFarmaceutico(
            farmaceuticoId: Int
    ): ResponseEntity<FarmaceuticoDto> {
        val resultado = farmaceuticoService.getFarmaceutico(farmaceuticoId)
        return ResponseEntity.ok(resultado)
    }

    override fun putFarmaceuticoFarmaceuticoId(
            farmaceuticoId: Int,
            farmaceuticoDto: FarmaceuticoDto?
    ): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }
}
