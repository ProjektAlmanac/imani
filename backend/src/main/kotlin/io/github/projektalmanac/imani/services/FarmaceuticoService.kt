package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.exceptions.CuerpoDePeticionNuloException
import io.github.projektalmanac.imani.exceptions.NombreUsuarioTomadoException
import io.github.projektalmanac.imani.generated.dto.NuevoFarmaceuticoDto
import io.github.projektalmanac.imani.mappers.FarmaceuticoMapper
import io.github.projektalmanac.imani.repositories.DoctorRepository
import io.github.projektalmanac.imani.repositories.FarmaceuticoRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class FarmaceuticoService(
    private val doctorRepository: DoctorRepository,
    private val farmaceuticoRepository: FarmaceuticoRepository,
    private val passwordEncoder: PasswordEncoder,
    private val farmaceuticoMapper: FarmaceuticoMapper
) {
    fun addFarmaceutico(nuevoFarmaceuticoDto: NuevoFarmaceuticoDto?) {
        if (nuevoFarmaceuticoDto === null) throw CuerpoDePeticionNuloException()

        val doctorExistente = doctorRepository.findDoctorByNombreUsuario(nuevoFarmaceuticoDto.nombreUsuario)
        if (doctorExistente !== null) throw NombreUsuarioTomadoException(nuevoFarmaceuticoDto.nombreUsuario)

        val farmaceuticoExistente = farmaceuticoRepository.findFarmaceuticoByNombreUsuario(nuevoFarmaceuticoDto.nombreUsuario)
        if (farmaceuticoExistente !== null) throw NombreUsuarioTomadoException(nuevoFarmaceuticoDto.nombreUsuario)

        val passwordHash = passwordEncoder.encode(nuevoFarmaceuticoDto.password)

        val farmaceutico = farmaceuticoMapper.toFarmaceutico(nuevoFarmaceuticoDto, passwordHash)
        farmaceuticoRepository.save(farmaceutico)
    }
}
