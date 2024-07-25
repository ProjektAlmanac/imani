package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.entities.Prescripcion
import io.github.projektalmanac.imani.exceptions.UsuarioNoEncontradoException
import io.github.projektalmanac.imani.generated.dto.PrescripcionDto
import io.github.projektalmanac.imani.mappers.PrescripccionMapper
import io.github.projektalmanac.imani.repositories.PacienteRepository
import io.github.projektalmanac.imani.repositories.PrescripcionRepository
import org.springframework.stereotype.Service

@Service
class PrescripccionService(
    private val prescripcionRepository: PrescripcionRepository,
    private val pacienteRepository: PacienteRepository,
    private val prescripccionMapper: PrescripccionMapper
    ) {
    fun getPrescripciones(pacienteId: Int): List<PrescripcionDto>{
        var prescripcionesPaciente = mutableListOf<PrescripcionDto>()
        val paciente = pacienteRepository.findById(pacienteId).orElseThrow { UsuarioNoEncontradoException(pacienteId) }
        val prescripciones = paciente.prescripciones
        if (prescripciones.isEmpty()){
            return prescripcionesPaciente
        }
        for (prescripcion in prescripciones){
            prescripcionesPaciente.add(prescripccionMapper.toPrescripcionDto(prescripcion))
        }
        return prescripcionesPaciente
    }
}