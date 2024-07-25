package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.entities.Paciente
import io.github.projektalmanac.imani.exceptions.CuerpoDePeticionNuloException
import io.github.projektalmanac.imani.exceptions.UsuarioNoEncontradoException
import io.github.projektalmanac.imani.generated.dto.PacienteDto
import io.github.projektalmanac.imani.mappers.PacienteMapper
import io.github.projektalmanac.imani.repositories.PacienteRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PacienteService (
    private val pacienteRepository: PacienteRepository,
    private val pacienteMapper: PacienteMapper
){

    fun addPaciente(pacienteDto: PacienteDto?): PacienteDto {
        if (pacienteDto === null) throw CuerpoDePeticionNuloException()
        // Mapea de PasienteDTO a la entidad Pasiente
        val paciente = pacienteMapper.toPaciente(pacienteDto)
        // Se crea un token UUID para el paciente
        paciente.token = UUID.randomUUID().toString()
        // Se persiste en base de datos la entidad
        val pacienteGuardado = pacienteRepository.save(paciente)
        return pacienteMapper.toPacienteDto(pacienteGuardado)
    }

    fun getPaciente(pacienteId: Int): PacienteDto {
        val paciente = pacienteRepository.findById(pacienteId).orElseThrow { throw UsuarioNoEncontradoException(pacienteId) }
        return pacienteMapper.toPacienteDto(paciente)
    }

    fun updatePaciente(pacienteId: Int, pacienteDto: PacienteDto?) {
        if (pacienteDto === null) throw CuerpoDePeticionNuloException()
        val paciente = pacienteRepository.findById(pacienteId).orElseThrow { throw UsuarioNoEncontradoException(pacienteId) }
        val pacienteActualizado = pacienteMapper.toPaciente(pacienteDto)
        pacienteActualizado.id = paciente.id
        pacienteActualizado.token = paciente.token
        val pacienteGuardado = pacienteRepository.save(pacienteActualizado)
        pacienteMapper.toPacienteDto(pacienteGuardado)
    }
}
