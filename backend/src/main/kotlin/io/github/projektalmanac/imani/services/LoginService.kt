package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.exceptions.CuerpoDePeticionNuloException
import io.github.projektalmanac.imani.exceptions.`CredencialesInvalidasException`
import io.github.projektalmanac.imani.generated.dto.LoginDto
import io.github.projektalmanac.imani.generated.dto.UsuarioDto
import io.github.projektalmanac.imani.mappers.LoginMapper
import io.github.projektalmanac.imani.repositories.DoctorRepository
import io.github.projektalmanac.imani.repositories.FarmaceuticoRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val doctorRepository: DoctorRepository,
    private val farmaceuticoRepository: FarmaceuticoRepository,
    private val loginMapper: LoginMapper,
    private val passwordEncoder: PasswordEncoder
) {

    fun iniciarSesion(loginDto: LoginDto?): UsuarioDto {
        if (loginDto == null) throw CuerpoDePeticionNuloException()

        val nombreUsuario = loginDto.usuario

        val doctor = doctorRepository.findDoctorByNombreUsuario(nombreUsuario)
        if (doctor != null && passwordEncoder.matches(loginDto.password, doctor.passwordHash)) {
            return loginMapper.mapDoctorToUsuarioDto(doctor)
        }

        val farmaceutico = farmaceuticoRepository.findFarmaceuticoByNombreUsuario(nombreUsuario)
        if (farmaceutico != null && passwordEncoder.matches(loginDto.password, farmaceutico.passwordHash)) {
            return loginMapper.mapFarmaceuticoToUsuarioDto(farmaceutico)
        }

        throw CredencialesInvalidasException()
    }

}
