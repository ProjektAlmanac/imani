package io.github.projektalmanac.imani.mappers

import io.github.projektalmanac.imani.entities.Doctor
import io.github.projektalmanac.imani.entities.Farmaceutico
import io.github.projektalmanac.imani.generated.dto.UsuarioDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named

@Mapper(componentModel = "spring", uses = [JsonNullableMapper::class])
abstract class LoginMapper {
    @Mapping(target = "apellido", source = "doctor.apellidos")
    @Mapping(target = "rol", source = "rol", qualifiedByName = ["mapRol"])
    abstract fun mapDoctorToUsuarioDto(doctor: Doctor, rol: String): UsuarioDto

    @Mapping(target = "apellido", source = "farmaceutico.apellidos")
    @Mapping(target = "rol", source = "rol", qualifiedByName = ["mapRol"])
    abstract fun mapFarmaceuticoToUsuarioDto(farmaceutico: Farmaceutico, rol: String): UsuarioDto

    @Named("mapRol")
    fun mapRol(rol: String): UsuarioDto.Rol {
        return UsuarioDto.Rol.valueOf(rol)
    }
}