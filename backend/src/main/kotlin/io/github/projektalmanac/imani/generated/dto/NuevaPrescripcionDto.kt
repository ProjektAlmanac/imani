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
 * @param medicamento 
 * @param frecuenciaDosis 
 * @param indicaciones 
 * @param duracion 
 * @param numeroDeDosis La cantidad que tiene la caja o la cantidad que le queda al paciente en estos momentos 
 * @param cantidadPorDosis cuanto tiene que tomar cada vez 
 * @param inicio 
 * @param identificador 
 * @param idDoctor 
 */
data class NuevaPrescripcionDto(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("medicamento", required = true) val medicamento: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("frecuenciaDosis", required = true) val frecuenciaDosis: kotlin.Int,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("indicaciones", required = true) val indicaciones: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("duracion", required = true) val duracion: kotlin.Int,

    @Schema(example = "null", required = true, description = "La cantidad que tiene la caja o la cantidad que le queda al paciente en estos momentos ")
    @get:JsonProperty("numeroDeDosis", required = true) val numeroDeDosis: java.math.BigDecimal,

    @Schema(example = "null", required = true, description = "cuanto tiene que tomar cada vez ")
    @get:JsonProperty("cantidadPorDosis", required = true) val cantidadPorDosis: java.math.BigDecimal,

    @Schema(example = "null", description = "")
    @get:JsonProperty("inicio") val inicio: java.time.OffsetDateTime? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("identificador") val identificador: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("idDoctor") val idDoctor: kotlin.Int? = null
) {

}

