package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.entities.Farmaceutico
import io.github.projektalmanac.imani.exceptions.CuerpoDePeticionNuloException
import io.github.projektalmanac.imani.exceptions.FarmaceuticoNotFoundException
import io.github.projektalmanac.imani.exceptions.NombreUsuarioTomadoException
import io.github.projektalmanac.imani.exceptions.PacienteNotFoundException
import io.github.projektalmanac.imani.generated.dto.FarmaceuticoDto
import io.github.projektalmanac.imani.generated.dto.NuevoFarmaceuticoDto
import io.github.projektalmanac.imani.mappers.FarmaceuticoMapper
import io.github.projektalmanac.imani.repositories.DoctorRepository
import io.github.projektalmanac.imani.repositories.FarmaceuticoRepository
import io.github.projektalmanac.imani.repositories.PacienteRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class FarmaceuticoService(
    private val doctorRepository: DoctorRepository,
    private val farmaceuticoRepository: FarmaceuticoRepository,
    private val passwordEncoder: PasswordEncoder,
    private val farmaceuticoMapper: FarmaceuticoMapper,
    private val pacienteRepository: PacienteRepository
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

    fun getFarmaceutico(farmaceuticoId: Int): FarmaceuticoDto {
        val farmaceutico = farmaceuticoRepository.findById(farmaceuticoId).orElseThrow { FarmaceuticoNotFoundException(farmaceuticoId) }

        return farmaceuticoMapper.toFarmaceuticoDto(farmaceutico)
    }

    fun agregarPacienteFarmaceutico(idUser: Int, pacienteId: Int) {
        val farmaceutico = farmaceuticoRepository.findById(idUser).orElseThrow { FarmaceuticoNotFoundException(idUser) }
        val paciente = pacienteRepository.findById(pacienteId).orElseThrow { PacienteNotFoundException(pacienteId) }
        farmaceutico.pacientaAtendido = paciente
        pacienteRepository.save(paciente)
    }

    fun updateFarmaceutico(farmaceuticoId: Int, farmaceuticoDto: FarmaceuticoDto?) {
        if (farmaceuticoDto === null) throw CuerpoDePeticionNuloException()
        val farmaceutico = farmaceuticoRepository.findById(farmaceuticoId).orElseThrow { FarmaceuticoNotFoundException(farmaceuticoId) }
        val paciente = farmaceuticoDto.idPaciente?.let { pacienteRepository.findById(it).orElseThrow { PacienteNotFoundException(it) } }
        farmaceuticoMapper.update(farmaceutico, farmaceuticoDto)
        farmaceutico.pacientaAtendido = paciente
        farmaceuticoRepository.save(farmaceutico)
    }
}
