package io.github.projektalmanac.imani.mappers

import io.github.projektalmanac.imani.entities.Figura
import io.github.projektalmanac.imani.entities.Paciente
import io.github.projektalmanac.imani.entities.Prescripcion
import io.github.projektalmanac.imani.generated.dto.NuevaPrescripcionDto
import io.github.projektalmanac.imani.generated.dto.PrescripcionDto
import org.mapstruct.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset

@Mapper(componentModel = "spring", uses = [JsonNullableMapper::class])
abstract class PrescripcionMapper {

    @Mapping(source = "dto.inicio", target = "inicio", qualifiedByName = ["offsetDateTimeToLocalDateTime"])
    @Mapping(target = "id", expression = "java(0)")
    abstract fun toPrescripcion(dto: NuevaPrescripcionDto?, paciente: Paciente, figura: Figura): Prescripcion?

    @Named("offsetDateTimeToLocalDateTime")
    fun offsetDateTimeToLocalDateTime(offsetDateTime: OffsetDateTime?): LocalDateTime? {
        return offsetDateTime?.toLocalDateTime()
    }

    @Named("localDateTimeToOffsetDateTime")
    fun localDateTimeToOffsetDateTime(localDateTime: LocalDateTime?): OffsetDateTime? {
        return localDateTime?.atOffset(ZoneOffset.UTC)
    }

    @Named("mapFigura")
    @ValueMapping(source = "CUADRADO", target = "cuadrado")
    @ValueMapping(source = "CIRCULO", target = "circulo")
    @ValueMapping(source = "TRIANGULO", target = "triangulo")
    @ValueMapping(source = "ESTRELLA", target = "estrella")
    @ValueMapping(source = "ANILLO", target = "anillo")
    @ValueMapping(source = "SEMICIRCULO", target = "semicirculo")
    @ValueMapping(source = "NUBE", target = "nube")
    abstract fun mapFigura(figura: Figura): PrescripcionDto.Figura

    @Mapping(source = "figura", target = "figura", qualifiedByName = ["mapFigura"])
    @Mapping(source = "inicio", target = "inicio", qualifiedByName = ["localDateTimeToOffsetDateTime"])
    abstract fun toPrescripcionDto(prescripcion: Prescripcion): PrescripcionDto

    @Mapping(target = "inicio", qualifiedByName = ["offsetDateTimeToLocalDateTime"])
    abstract fun update(@MappingTarget prescripcion: Prescripcion, prescripcionDto: NuevaPrescripcionDto)
}


