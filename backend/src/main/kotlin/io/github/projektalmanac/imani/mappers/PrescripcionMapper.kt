package io.github.projektalmanac.imani.mappers

import io.github.projektalmanac.imani.entities.Paciente
import io.github.projektalmanac.imani.entities.Prescripcion
import io.github.projektalmanac.imani.generated.dto.NuevaPrescripcionDto
import org.mapstruct.*
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Mapper(componentModel = "spring", uses = [JsonNullableMapper::class])
abstract class PrescripcionMapper {

    @Mapping(source = "inicio", target = "inicio", qualifiedByName = ["offsetDateTimeToLocalDateTime"])
    @Mapping(target = "paciente", ignore = true)
    @ValueMappings(
        ValueMapping(source = "cuadrado", target = "CUADRADO"),
        ValueMapping(source = "circulo", target = "CIRCULO"),
        ValueMapping(source = "triangulo", target = "TRIANGULO"),
        ValueMapping(source = "estrella", target = "ESTRELLA"),
        ValueMapping(source = "anillo", target = "ANILLO"),
        ValueMapping(source = "semicirculo", target = "SEMICIRCULO"),
        ValueMapping(source = "nube", target = "NUBE")
    )
    abstract fun toPrescripcion(dto: NuevaPrescripcionDto?, @Context paciente2: Paciente): Prescripcion?

    @AfterMapping
    protected fun setPaciente(@MappingTarget prescripcion: Prescripcion?, @Context paciente2: Paciente) {
        if (prescripcion != null) {
            prescripcion.paciente = paciente2
        }
    }

    @Named("offsetDateTimeToLocalDateTime")
    fun offsetDateTimeToLocalDateTime(offsetDateTime: OffsetDateTime?): LocalDateTime? {
        return offsetDateTime?.toLocalDateTime()
    }
}
