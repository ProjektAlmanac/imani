package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.exceptions.UsuarioNoEncontradoException
import io.github.projektalmanac.imani.exceptions.CuerpoDePeticionNuloException
import io.github.projektalmanac.imani.exceptions.PrescripcionVaciaException
import io.github.projektalmanac.imani.generated.dto.NuevaPrescripcionDto
import io.github.projektalmanac.imani.mappers.PrescripcionMapper
import io.github.projektalmanac.imani.repositories.PacienteRepository
import org.springframework.stereotype.Service
import java.net.URI

@Service
class PrescripcionService(val pacienteRepository: PacienteRepository,
                          val prescripcionMapper: PrescripcionMapper) {

    fun guardarPrescripcion(pacienteId: Int, prescripcion: List<NuevaPrescripcionDto>): URI {

        if (prescripcion === null) throw CuerpoDePeticionNuloException()

        if (prescripcion.isEmpty()) throw PrescripcionVaciaException()

        val paciente = pacienteRepository.findById(pacienteId).orElseThrow {
            UsuarioNoEncontradoException(pacienteId)
        }

        prescripcion.forEach {
            val result = prescripcionMapper.toPrescripcion(it, paciente)
            if (result != null) {
                paciente.prescripciones.add(result)
            }
        }
        pacienteRepository.save(paciente)

        return URI.create("/pacientes/$pacienteId/prescripciones")

    }
}
