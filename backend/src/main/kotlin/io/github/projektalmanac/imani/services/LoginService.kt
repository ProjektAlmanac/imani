package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.exceptions.CuerpoDePeticionNuloException
import io.github.projektalmanac.imani.exceptions.NombreUsuarioNoEncontradoException
import io.github.projektalmanac.imani.generated.dto.LoginDto
import io.github.projektalmanac.imani.generated.dto.UsuarioDto
import io.github.projektalmanac.imani.mappers.LoginMapper
import io.github.projektalmanac.imani.repositories.DoctorRepository
import io.github.projektalmanac.imani.repositories.FarmaceuticoRepository
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val doctorRepository: DoctorRepository,
    private val farmaceuticoRepository: FarmaceuticoRepository,
    private val loginMapper: LoginMapper) {

    fun obtenInformacionUsuario(loginDto: LoginDto?): UsuarioDto {
        if (loginDto == null) throw CuerpoDePeticionNuloException()

        val nombreUsuario = loginDto.usuario

        val doctor = doctorRepository.findDoctorByNombreUsuario(nombreUsuario)
        if (doctor != null) {
            return loginMapper.mapDoctorToUsuarioDto(doctor, "doctor")
        }

        val farmaceutico = farmaceuticoRepository.findFarmaceuticoByNombreUsuario(nombreUsuario)
        if (farmaceutico != null) {
            return loginMapper.mapFarmaceuticoToUsuarioDto(farmaceutico, "farmaceutico")
        }

        throw NombreUsuarioNoEncontradoException(nombreUsuario)
    }

}