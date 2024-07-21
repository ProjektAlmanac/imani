package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.LoginApi
import io.github.projektalmanac.imani.generated.dto.LoginDto
import io.github.projektalmanac.imani.generated.dto.UsuarioDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController : LoginApi {
    override fun login(loginDto: LoginDto?): ResponseEntity<UsuarioDto> {
        TODO("Not yet implemented")
    }
}
