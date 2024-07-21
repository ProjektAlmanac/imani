package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.PrescripcionesApi
import io.github.projektalmanac.imani.generated.dto.NuevaPrescripcionDto
import io.github.projektalmanac.imani.generated.dto.PrescripcionDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PrescripcionController : PrescripcionesApi {
    override fun getPrescription(pacienteId: Int): ResponseEntity<List<PrescripcionDto>> {
        TODO("Not yet implemented")
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
