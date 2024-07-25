package io.github.projektalmanac.imani.mappers

import io.github.projektalmanac.imani.entities.Figura
import io.github.projektalmanac.imani.entities.Paciente
import io.github.projektalmanac.imani.entities.Prescripcion
import io.github.projektalmanac.imani.generated.dto.NuevaPrescripcionDto
import io.github.projektalmanac.imani.generated.dto.PrescripcionDto
import org.mapstruct.*
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset

@Mapper(componentModel = "spring")
abstract class PrescripccionMapper {

    @Mapping(source = "inicio", target = "inicio", qualifiedByName = ["offsetDateTimeToLocalDateTime"])
    @Mapping(target = "paciente", ignore = true)
    abstract fun toPrescripcion(dto: NuevaPrescripcionDto?, @Context paciente: Paciente, @Context figura: Figura): Prescripcion?

    @AfterMapping
    protected fun setPaciente(@MappingTarget prescripcion: Prescripcion?, @Context paciente: Paciente, @Context figura: Figura) {
        if (prescripcion != null) {
            prescripcion.paciente = paciente
            prescripcion.figura = figura
        }
    }

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
}