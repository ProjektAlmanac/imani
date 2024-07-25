package io.github.projektalmanac.imani.mappers

import io.github.projektalmanac.imani.entities.Farmaceutico
import io.github.projektalmanac.imani.generated.dto.FarmaceuticoDto
import io.github.projektalmanac.imani.generated.dto.NuevoFarmaceuticoDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring")
abstract class FarmaceuticoMapper {
    abstract fun toFarmaceutico(nuevoFarmaceuticoDto: NuevoFarmaceuticoDto, passwordHash: String): Farmaceutico
    @Mapping(target = "idPaciente", source = "pacientaAtendido.id")
    abstract fun toFarmaceuticoDto(farmaceutico: Farmaceutico): FarmaceuticoDto
    abstract fun update(@MappingTarget farmaceutico: Farmaceutico, farmaceuticoDto: FarmaceuticoDto)
}
