package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.DoctorApi
import io.github.projektalmanac.imani.generated.dto.NuevoDoctorDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class DoctorController : DoctorApi {
    override fun postDoctor(nuevoDoctorDto: NuevoDoctorDto?): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }
}
