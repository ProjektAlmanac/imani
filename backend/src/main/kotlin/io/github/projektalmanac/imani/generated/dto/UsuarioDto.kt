package io.github.projektalmanac.imani.generated.dto

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
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
 * @param nombre 
 * @param apellido 
 * @param rol 
 */
data class UsuarioDto(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("id", required = true) val id: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("Nombre", required = true) val nombre: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("Apellido", required = true) val apellido: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("rol", required = true) val rol: UsuarioDto.Rol
) {

    /**
    * 
    * Values: doctor,farmaceutico
    */
    enum class Rol(val value: kotlin.String) {

        @JsonProperty("doctor") doctor("doctor"),
        @JsonProperty("farmaceutico") farmaceutico("farmaceutico")
    }

}

