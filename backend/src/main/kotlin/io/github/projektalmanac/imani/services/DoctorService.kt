package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.exceptions.CuerpoDePeticionNuloException
import io.github.projektalmanac.imani.exceptions.NombreUsuarioTomadoException
import io.github.projektalmanac.imani.generated.dto.NuevoDoctorDto
import io.github.projektalmanac.imani.mappers.DoctorMapper
import io.github.projektalmanac.imani.repositories.DoctorRepository
import io.github.projektalmanac.imani.repositories.FarmaceuticoRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class DoctorService(
    private val doctorMapper: DoctorMapper,
    private val doctorRepository: DoctorRepository,
    private val farmaceuticoRepository: FarmaceuticoRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun addDoctor(nuevoDoctorDto: NuevoDoctorDto?) {
        if (nuevoDoctorDto === null) throw CuerpoDePeticionNuloException()

        val doctorExistente = doctorRepository.findDoctorByNombreUsuario(nuevoDoctorDto.nombreUsuario)
        if (doctorExistente !== null) throw NombreUsuarioTomadoException(nuevoDoctorDto.nombreUsuario)

        val farmaceuticoExistente = farmaceuticoRepository.findFarmaceuticoByNombreUsuario(nuevoDoctorDto.nombreUsuario)
        if (farmaceuticoExistente !== null) throw NombreUsuarioTomadoException(nuevoDoctorDto.nombreUsuario)

        val passwordHash = passwordEncoder.encode(nuevoDoctorDto.password)

        val doctor = doctorMapper.toDoctor(nuevoDoctorDto, passwordHash)
        doctorRepository.save(doctor)
    }
}
