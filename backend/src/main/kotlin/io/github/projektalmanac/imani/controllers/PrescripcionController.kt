package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.PrescripcionesApi
import io.github.projektalmanac.imani.generated.dto.NuevaPrescripcionDto
import io.github.projektalmanac.imani.generated.dto.PrescripcionDto
import io.github.projektalmanac.imani.services.PrescripccionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PrescripcionController(private val prescripccionService: PrescripccionService) : PrescripcionesApi {
    override fun getPrescription(pacienteId: Int): ResponseEntity<List<PrescripcionDto>> {
       return ResponseEntity.ok(prescripccionService.getPrescripciones(pacienteId))
    }

    override fun postPrescription(
        pacienteId: Int,
        nuevaPrescripcionDto: List<NuevaPrescripcionDto>?
    ): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }

    override fun putPrescripcion(
        pacienteId: Int,
        prescripcionId: Int,
        nuevaPrescripcionDto: NuevaPrescripcionDto?
    ): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }
}
