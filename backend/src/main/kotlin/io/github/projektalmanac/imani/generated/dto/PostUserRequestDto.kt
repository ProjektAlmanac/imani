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
 * @param firstName 
 * @param lastName 
 * @param email 
 * @param dateOfBirth 
 */
data class PostUserRequestDto(

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("firstName", required = true) val firstName: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("lastName", required = true) val lastName: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("email", required = true) val email: kotlin.String,

    @field:Valid
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("dateOfBirth", required = true) val dateOfBirth: java.time.LocalDate
) {

}

