package io.github.projektalmanac.imani.mappers

import io.github.projektalmanac.imani.entities.Paciente
import io.github.projektalmanac.imani.generated.dto.PacienteDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
abstract class PacienteMapper {
    @Mapping(source = "fechaDeNacimiento", target = "fechaNacimiento")
    abstract fun toPaciente(pacienteDto: PacienteDto): Paciente
    @Mapping(source = "fechaNacimiento", target = "fechaDeNacimiento")
    abstract fun toPacienteDto(paciente: Paciente): PacienteDto
}