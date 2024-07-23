package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.DoctorApi
import io.github.projektalmanac.imani.generated.dto.NuevoDoctorDto
import io.github.projektalmanac.imani.services.DoctorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DoctorController(val doctorService: DoctorService) : DoctorApi {
    override fun postDoctor(nuevoDoctorDto: NuevoDoctorDto?): ResponseEntity<Unit> {
        doctorService.addDoctor(nuevoDoctorDto)

        return ResponseEntity.noContent().build()
    }
}
