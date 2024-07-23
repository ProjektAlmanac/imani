package io.github.projektalmanac.imani.mappers

import io.github.projektalmanac.imani.entities.Doctor
import io.github.projektalmanac.imani.generated.dto.NuevoDoctorDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
abstract class DoctorMapper {
    abstract fun toDoctor(nuevoDoctorDto: NuevoDoctorDto, passwordHash: String): Doctor
}
