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
 * @param email If a new email is given, the user's email verified property will be set to false.
 * @param dateOfBirth 
 */
data class PatchUsersUserIdRequestDto(

    @Schema(example = "null", description = "")
    @get:JsonProperty("firstName") val firstName: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("lastName") val lastName: kotlin.String? = null,

    @Schema(example = "null", description = "If a new email is given, the user's email verified property will be set to false.")
    @get:JsonProperty("email") val email: kotlin.String? = null,

    @Schema(example = "null", description = "")
    @get:JsonProperty("dateOfBirth") val dateOfBirth: kotlin.String? = null
) {

}

