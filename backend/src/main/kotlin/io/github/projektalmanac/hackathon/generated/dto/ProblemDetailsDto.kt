package io.github.projektalmanac.hackathon.generated.dto

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
 * @param type 
 * @param detail 
 * @param status 
 */
data class ProblemDetailsDto(

    @Schema(example = "null", description = "")
    @get:JsonProperty("type") val type: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("detail") val detail: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("status") val status: kotlin.Int? = null
) {

}

