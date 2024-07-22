package io.github.projektalmanac.imani.mappers

import io.github.projektalmanac.imani.entities.Farmaceutico
import io.github.projektalmanac.imani.generated.dto.NuevoFarmaceuticoDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
abstract class FarmaceuticoMapper {
    abstract fun toFarmaceutico(nuevoFarmaceuticoDto: NuevoFarmaceuticoDto, passwordHash: String): Farmaceutico
}
