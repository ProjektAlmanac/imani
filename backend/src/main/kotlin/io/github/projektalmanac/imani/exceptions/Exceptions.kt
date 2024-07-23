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

class PrescripcionVaciaException(cause: Throwable? = null) : AppException("La prescripción no puede estar vacía", cause) {
    override val tipo = "PRESCRIPCION_VACIA"
    override val status = HttpStatus.BAD_REQUEST
}

class GuardarPrescripcionException(id: Int, cause: Throwable? = null) : AppException("No se pudo guardar la prescripción del usuario con ID '$id'", cause) {
    override val tipo = "GUARDAR_PRESCRIPCION"
    override val status = HttpStatus.INTERNAL_SERVER_ERROR
}
