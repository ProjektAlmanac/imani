package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.CitaApi
import io.github.projektalmanac.imani.generated.dto.CitasDto
import io.github.projektalmanac.imani.generated.dto.NuevaCitaDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class CitaController : CitaApi{
    override fun getDates(pacienteId: Int): ResponseEntity<List<CitasDto>> {
        TODO("Not yet implemented")
    }

    override fun postDate(pacienteId: Int, nuevaCitaDto: NuevaCitaDto?): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }
}
