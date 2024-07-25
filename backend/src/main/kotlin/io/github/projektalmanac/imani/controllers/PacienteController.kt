package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.PacienteApi
import io.github.projektalmanac.imani.generated.dto.PacienteDto
import io.github.projektalmanac.imani.services.PacienteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PacienteController (val pacienteService: PacienteService): PacienteApi {
    override fun getPacienteID(pacienteId: Int): ResponseEntity<PacienteDto> {
        val paciente = pacienteService.getPaciente(pacienteId)
        return ResponseEntity.ok(paciente)
    }

    override fun getPacientesDoctorID(doctorId: Int): ResponseEntity<List<PacienteDto>> {
        val pacientes = pacienteService.getPacientes(doctorId)
        return ResponseEntity.ok(pacientes)
    }

    override fun postPacientes(pacienteDto: PacienteDto?): ResponseEntity<PacienteDto> {
        val paciente = pacienteService.addPaciente(pacienteDto)
        return ResponseEntity.ok(paciente)
    }

    override fun upDatePacienteID(pacienteId: Int, pacienteDto: PacienteDto?): ResponseEntity<Unit> {
        pacienteService.updatePaciente(pacienteId, pacienteDto)
        return ResponseEntity.noContent().build()
    }
}
