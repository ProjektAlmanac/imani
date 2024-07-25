package io.github.projektalmanac.imani.mappers

import io.github.projektalmanac.imani.entities.Doctor
import io.github.projektalmanac.imani.entities.Farmaceutico
import io.github.projektalmanac.imani.generated.dto.UsuarioDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named

@Mapper(componentModel = "spring", uses = [JsonNullableMapper::class])
abstract class LoginMapper {
    @Mapping(target = "apellido", source = "apellidos")
    @Mapping(target = "rol", constant = "doctor", qualifiedByName = ["mapRol"])
    abstract fun mapDoctorToUsuarioDto(doctor: Doctor): UsuarioDto

    @Mapping(target = "apellido", source = "apellidos")
    @Mapping(target = "rol", constant = "farmaceutico", qualifiedByName = ["mapRol"])
    abstract fun mapFarmaceuticoToUsuarioDto(farmaceutico: Farmaceutico): UsuarioDto

    @Named("mapRol")
    fun mapRol(rol: String): UsuarioDto.Rol {
        return UsuarioDto.Rol.valueOf(rol)
    }
}
