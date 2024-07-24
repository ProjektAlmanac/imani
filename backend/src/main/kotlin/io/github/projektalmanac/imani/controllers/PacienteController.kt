package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.PacienteApi
import io.github.projektalmanac.imani.generated.dto.PacienteDto
import io.github.projektalmanac.imani.services.PacienteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PacienteController (val pasienteService: PacienteService): PacienteApi {
    override fun getPacienteID(pacienteId: Int): ResponseEntity<PacienteDto> {
        TODO("Not yet implemented")
    }

    override fun getPacientesDoctorID(doctorId: String): ResponseEntity<List<PacienteDto>> {
        TODO("Not yet implemented")
    }

    override fun postPacientes(pacienteDto: PacienteDto?): ResponseEntity<PacienteDto> {
        val paciente = pasienteService.addPaciente(pacienteDto)
        return ResponseEntity.ok(paciente)
    }

    override fun upDatePacienteID(pacienteId: Int, pacienteDto: PacienteDto?): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }
}
