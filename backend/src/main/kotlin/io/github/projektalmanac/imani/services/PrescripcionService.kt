package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.entities.Figura
import io.github.projektalmanac.imani.entities.Paciente
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

    fun guardarPrescripcion(pacienteId: Int, prescripcion: List<NuevaPrescripcionDto>): URI {

        if (prescripcion.isEmpty()) throw PrescripcionVaciaException()

        val paciente = pacienteRepository.findById(pacienteId).orElseThrow {
            UsuarioNoEncontradoException(pacienteId)
        }

        prescripcion.forEach {
            val figura = asignarFigura(it.medicamento, paciente)
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

    private fun asignarFigura(medicamento: String, paciente: Paciente): Figura {

        paciente.prescripciones.forEach { prescripcion ->
            if (prescripcion.medicamento == medicamento) {
                return prescripcion.figura
            }
        }

        val ultimaFiguraAsignada = paciente.prescripciones.lastOrNull()?.figura
        val nuevasFiguras = Figura.values()

        if (ultimaFiguraAsignada == null) {
            return nuevasFiguras[0]
        }

        val indiceUltimaFigura = ultimaFiguraAsignada.ordinal
        if (indiceUltimaFigura < nuevasFiguras.size - 1) {
            return nuevasFiguras[indiceUltimaFigura + 1]
        }

        return nuevasFiguras[0]
    }

    fun obtenerPrescripciones(pacienteId: Int): List<PrescripcionDto> {
        val paciente = pacienteRepository.findById(pacienteId).orElseThrow {
            UsuarioNoEncontradoException(pacienteId)
        }
        return paciente.prescripciones.map { prescripcionMapper.toPrescripcionDto(it) }
    }
}

