package io.github.projektalmanac.imani.generated.dto

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import javax.validation.Valid
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 
 * @param archivo Archivo de imagen en base64
 */
data class PostPacientesSendQrRequestDto(

    @Schema(example = "null", description = "Archivo de imagen en base64")
    @get:JsonProperty("archivo") val archivo: kotlin.String? = null
) {

}

