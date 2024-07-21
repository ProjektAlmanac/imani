package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.PacienteApi
import io.github.projektalmanac.imani.generated.dto.PacienteDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PacienteController : PacienteApi {
    override fun getPacienteID(pacienteId: Int): ResponseEntity<PacienteDto> {
        TODO("Not yet implemented")
    }

    override fun getPacientesDoctorID(doctorId: String): ResponseEntity<List<PacienteDto>> {
        TODO("Not yet implemented")
    }

    override fun postPacientes(pacienteDto: PacienteDto?): ResponseEntity<PacienteDto> {
        TODO("Not yet implemented")
    }

    override fun upDatePacienteID(pacienteId: Int, pacienteDto: PacienteDto?): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }
}
