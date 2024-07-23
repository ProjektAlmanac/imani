package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.entities.Figura
import io.github.projektalmanac.imani.exceptions.GuardarPrescripcionException
import io.github.projektalmanac.imani.exceptions.UsuarioNoEncontradoException
import io.github.projektalmanac.imani.exceptions.PrescripcionVaciaException
import io.github.projektalmanac.imani.generated.dto.NuevaPrescripcionDto
import io.github.projektalmanac.imani.generated.dto.PrescripcionDto
import io.github.projektalmanac.imani.mappers.PrescripcionMapper
import io.github.projektalmanac.imani.repositories.PacienteRepository
import org.springframework.stereotype.Service
import java.net.URI

@Service
class PrescripcionService(
    private val pacienteRepository: PacienteRepository,
    private val prescripcionMapper: PrescripcionMapper
) {

    private val figuraMapping: MutableMap<String, Figura> = mutableMapOf()
    private val figurasDisponibles = Figura.values().toMutableList()

    fun guardarPrescripcion(pacienteId: Int, prescripcion: List<NuevaPrescripcionDto>): URI {

        if (prescripcion.isEmpty()) throw PrescripcionVaciaException()

        val paciente = pacienteRepository.findById(pacienteId).orElseThrow {
            UsuarioNoEncontradoException(pacienteId)
        }

        prescripcion.forEach {
            val figura = asignarFigura(it.medicamento)
            val result = prescripcionMapper.toPrescripcion(it, paciente, figura)
            if (result != null) {
                paciente.prescripciones.add(result)
            } else {
                throw GuardarPrescripcionException(pacienteId)
            }
        }
        pacienteRepository.save(paciente)

        return URI.create("/pacientes/$pacienteId/prescripciones")
    }

    private fun asignarFigura(medicamento: String): Figura {
        return figuraMapping.computeIfAbsent(medicamento) {
            if (figurasDisponibles.isNotEmpty()) {
                figurasDisponibles.removeAt(0)
            } else {
                throw IllegalStateException("No hay más figuras disponibles para asignar")
            }
        }
    }

    fun obtenerPrescripciones(pacienteId: Int): List<PrescripcionDto> {
        val paciente = pacienteRepository.findById(pacienteId).orElseThrow {
            UsuarioNoEncontradoException(pacienteId)
        }
        return paciente.prescripciones.map { prescripcionMapper.toPrescripcionDto(it) }
    }
}

