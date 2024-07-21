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
 * @param id 
 * @param fecha 
 * @param nombreDoctor 
 * @param apellidoDoctor 
 * @param centroMedico 
 */
data class CitasDto(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("id", required = true) val id: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("fecha", required = true) val fecha: java.time.OffsetDateTime,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("nombreDoctor", required = true) val nombreDoctor: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("apellidoDoctor", required = true) val apellidoDoctor: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("centroMedico", required = true) val centroMedico: kotlin.String
) {

}

