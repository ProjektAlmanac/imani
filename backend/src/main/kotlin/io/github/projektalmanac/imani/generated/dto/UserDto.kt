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
 * @param firstName 
 * @param lastName 
 * @param email 
 * @param emailVerified Set to true if the user's email has been verified.
 * @param dateOfBirth 
 * @param createDate The date that the user was created.
 */
data class UserDto(

    @Schema(example = "null", required = true, description = "Unique identifier for the given user.")
    @get:JsonProperty("id", required = true) val id: kotlin.Int,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("firstName", required = true) val firstName: kotlin.String,

    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("lastName", required = true) val lastName: kotlin.String,

    @get:Email
    @Schema(example = "null", required = true, description = "")
    @get:JsonProperty("email", required = true) val email: kotlin.String,

    @Schema(example = "null", required = true, description = "Set to true if the user's email has been verified.")
    @get:JsonProperty("emailVerified", required = true) val emailVerified: kotlin.Boolean,

    @field:Valid
    @Schema(example = "Thu Oct 30 18:00:00 CST 1997", description = "")
    @get:JsonProperty("dateOfBirth") val dateOfBirth: java.time.LocalDate? = null,

    @field:Valid
    @Schema(example = "null", description = "The date that the user was created.")
    @get:JsonProperty("createDate") val createDate: java.time.LocalDate? = null
) {

}

