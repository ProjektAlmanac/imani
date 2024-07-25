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
 * @param id ID único de la imagen
 * @param mensaje Mensaje de éxito
 */
data class PostPacientesSendQr200ResponseDto(

    @Schema(example = "null", description = "ID único de la imagen")
    @get:JsonProperty("id") val id: kotlin.String? = null,

    @Schema(example = "null", description = "Mensaje de éxito")
    @get:JsonProperty("mensaje") val mensaje: kotlin.String? = null
) {

}

