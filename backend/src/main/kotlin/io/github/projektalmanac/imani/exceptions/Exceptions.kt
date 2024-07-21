package io.github.projektalmanac.imani.exceptions

import org.springframework.http.HttpStatus

abstract class AppException(message: String, cause: Throwable? = null) : RuntimeException(message, cause) {
    abstract val tipo: String
    abstract val status: HttpStatus
}

class UsuarioNoEncontradoException(id: Int, cause: Throwable? = null) : AppException("No se encontró al usuario con ID '$id'", cause) {
    override val tipo = "USUARIO_NO_ENCONTRADO"
    override val status = HttpStatus.NOT_FOUND
}

class CuerpoDePeticionNuloException(cause: Throwable? = null) : AppException("El cuerpo de la petición no puede ser nulo", cause) {
    override val tipo = "CUERPO_NULO"
    override val status = HttpStatus.BAD_REQUEST
}

class NombreUsuarioTomadoException(nombreUsuario: String, cause: Throwable? = null) : AppException("El nombre de usuario '$nombreUsuario' ya está en uso", cause) {
    override val tipo = "NOMBRE_USUARIO_TOMADO"
    override val status = HttpStatus.BAD_REQUEST
}
