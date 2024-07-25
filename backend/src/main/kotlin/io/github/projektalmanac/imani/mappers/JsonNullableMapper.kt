package io.github.projektalmanac.imani.mappers

import org.mapstruct.Condition
import org.mapstruct.Mapper
import org.openapitools.jackson.nullable.JsonNullable

@Mapper(componentModel = "spring")
abstract class JsonNullableMapper {
    fun <T> unwrap(jsonNullable: JsonNullable<T?>): T? {
        return jsonNullable.orElse(null)
    }

    fun <T> wrap(`object`: T): JsonNullable<T> {
        return JsonNullable.of(`object`)
    }

    @Condition
    fun <T> isNotEmpty(field: JsonNullable<T>?): Boolean {
        return field != null && field.isPresent
    }
}
