package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.PrescripcionesApi
import io.github.projektalmanac.imani.generated.dto.NuevaPrescripcionDto
import io.github.projektalmanac.imani.generated.dto.PrescripcionDto
import io.github.projektalmanac.imani.services.PrescripcionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api")
class PrescripcionController(private val prescripcionService: PrescripcionService) : PrescripcionesApi {

    override fun getPrescription(pacienteId: Int): ResponseEntity<List<PrescripcionDto>> {
        TODO("Not yet implemented")
    }

    override fun postPrescription(
        pacienteId: Int,
        nuevaPrescripcionDto: List<NuevaPrescripcionDto>?
    ): ResponseEntity<Unit> {
        val location = prescripcionService.guardarPrescripcion(pacienteId, nuevaPrescripcionDto!!)
        return ResponseEntity.created(location).build()
    }

    override fun putPrescripcion(
        pacienteId: Int,
        prescripcionId: Int,
        nuevaPrescripcionDto: NuevaPrescripcionDto?
    ): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }
}
