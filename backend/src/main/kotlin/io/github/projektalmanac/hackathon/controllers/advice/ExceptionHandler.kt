package io.github.projektalmanac.hackathon.controllers.advice

import io.github.projektalmanac.hackathon.exceptions.AppException
import io.github.projektalmanac.hackathon.generated.dto.ProblemDetailsDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(AppException::class)
    fun handleAppException(ex: AppException) : ResponseEntity<ProblemDetailsDto> {
        return ResponseEntity
            .status(ex.status.value())
            .body(ProblemDetailsDto(ex.tipo, ex.message, ex.status.value()))
    }
}
