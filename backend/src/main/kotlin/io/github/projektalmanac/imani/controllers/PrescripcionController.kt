package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.PrescripcionesApi
import io.github.projektalmanac.imani.generated.dto.NuevaPrescripcionDto
import io.github.projektalmanac.imani.generated.dto.PrescripcionDto
import io.github.projektalmanac.imani.services.PacienteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class PrescripcionController (val pacienteService: PacienteService) : PrescripcionesApi {
    override fun getPrescription(pacienteId: Int): ResponseEntity<List<PrescripcionDto>> {
        val prescripcion=pacienteService.obtenerPrescripciones(pacienteId)
         return ResponseEntity.ok(prescripcion)
    }      
    

    override fun postPrescription(
        pacienteId: Int,
        nuevaPrescripcionDto: List<NuevaPrescripcionDto>?
    ): ResponseEntity<Unit> {
    pacienteService.agregarPrescripcion(pacienteId, nuevaPrescripcionDto)
        return ResponseEntity.noContent().build()
}

    override fun putPrescripcion(
        pacienteId: Int,
        prescripcionId: Int,
        nuevaPrescripcionDto: NuevaPrescripcionDto?
    ): ResponseEntity<Unit> {
        pacienteService.modificarPrescripcion(pacienteId, prescripcionId, nuevaPrescripcionDto)
        return ResponseEntity.noContent().build()
    }
}
