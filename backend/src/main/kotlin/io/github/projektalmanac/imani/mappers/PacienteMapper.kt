package io.github.projektalmanac.imani.mappers

import io.github.projektalmanac.imani.entities.Paciente
import io.github.projektalmanac.imani.generated.dto.PacienteDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import java.math.BigDecimal
import java.math.RoundingMode


@Mapper(componentModel = "spring")
abstract class PacienteMapper {
    @Mapping(source = "fechaDeNacimiento", target = "fechaNacimiento")
    abstract fun toPaciente(pacienteDto: PacienteDto): Paciente
    @Mapping(source = "fechaNacimiento", target = "fechaDeNacimiento")
    @Mapping(source = "estatura", target = "estatura", qualifiedByName = arrayOf("floatToBigDecimal"))
    abstract fun toPacienteDto(paciente: Paciente): PacienteDto

    @Named("floatToBigDecimal")
    protected fun floatToBigDecimal(estatura: Float?): BigDecimal? {
        return if (estatura != null) BigDecimal.valueOf(estatura.toDouble()).setScale(2, RoundingMode.HALF_UP) else null
    }
}
