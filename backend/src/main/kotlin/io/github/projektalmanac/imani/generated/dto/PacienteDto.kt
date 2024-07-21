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
 * @param id Unique identifier for the given user.
 * @param nombre 
 * @param apellidos 
 * @param fechaDeNacimiento 
 * @param estatura 
 * @param peso 
 * @param token 
 */
data class PacienteDto(

    @Schema(example = "null", required = true, description = "Unique identifier for the given user.")
    @get:JsonProperty("id", required = true) val id: kotlin.Int,

    @Schema(example = "null", description = "")
    @get:JsonProperty("nombre") val nombre: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("apellidos") val apellidos: kotlin.String? = null,

    @field:Valid
    @Schema(example = "Thu Oct 30 18:00:00 CST 1997", description = "")
    @get:JsonProperty("fechaDeNacimiento") val fechaDeNacimiento: java.time.LocalDate? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("estatura") val estatura: java.math.BigDecimal? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("peso") val peso: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("token") val token: kotlin.String? = null
) {

}

