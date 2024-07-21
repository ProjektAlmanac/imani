package io.github.projektalmanac.hackathon.controllers

import io.github.projektalmanac.hackathon.exceptions.UsuarioNoEncontradoException
import io.github.projektalmanac.hackathon.generated.api.DefaultApi
import io.github.projektalmanac.hackathon.generated.dto.PatchUsersUserIdRequestDto
import io.github.projektalmanac.hackathon.generated.dto.PostUserRequestDto
import io.github.projektalmanac.hackathon.generated.dto.UserDto
import io.github.projektalmanac.hackathon.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DefaultController(private val userService: UserService) : DefaultApi {
    override fun getUsersUserId(userId: Int): ResponseEntity<UserDto> {
        throw UsuarioNoEncontradoException(userId);
    }

    override fun patchUsersUserId(
        userId: Int,
        patchUsersUserIdRequestDto: PatchUsersUserIdRequestDto?
    ): ResponseEntity<UserDto> {
        TODO("Not yet implemented")
    }

    override fun postUser(postUserRequestDto: PostUserRequestDto?): ResponseEntity<UserDto> {
        val user = userService.addUser(postUserRequestDto)

        return ResponseEntity.ok(user)
    }

    @GetMapping("/test")
    fun test() = "Hello, World!"
}
