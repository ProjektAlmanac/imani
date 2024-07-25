package io.github.projektalmanac.imani.mappers

import io.github.projektalmanac.imani.entities.Doctor
import io.github.projektalmanac.imani.generated.dto.DoctorDto
import io.github.projektalmanac.imani.generated.dto.NuevoDoctorDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring")
abstract class DoctorMapper {
    abstract fun toDoctor(nuevoDoctorDto: NuevoDoctorDto, passwordHash: String): Doctor
    @Mapping(target = "idPaciente", source = "pacienteAtendido.id")
    abstract fun toDoctorDto(doctor: Doctor): DoctorDto
    abstract fun update(@MappingTarget doctor: Doctor, doctorDto: DoctorDto)
}
