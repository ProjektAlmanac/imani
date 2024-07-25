package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.LoginApi
import io.github.projektalmanac.imani.generated.dto.LoginDto
import io.github.projektalmanac.imani.generated.dto.UsuarioDto
import io.github.projektalmanac.imani.services.LoginService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class LoginController (private val loginService: LoginService): LoginApi {
    override fun login(loginDto: LoginDto?): ResponseEntity<UsuarioDto> {
        return ResponseEntity.ok(loginService.iniciarSesion(loginDto))
    }
}
