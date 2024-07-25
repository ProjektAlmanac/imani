package io.github.projektalmanac.imani.mappers

import io.github.projektalmanac.imani.entities.Prescripcion
import io.github.projektalmanac.imani.generated.dto.PrescripcionDto
import io.github.projektalmanac.imani.generated.dto.NuevaPrescripcionDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
abstract class PrescripcionMapper {
   
    abstract fun toPrescripcion(prescripcion:PrescripcionDto):Prescripcion
    abstract fun toPrescripcionDto(prescripcion:Prescripcion):PrescripcionDto
    abstract fun toPrescripcion(prescripcion:NuevaPrescripcionDto):Prescripcion

}