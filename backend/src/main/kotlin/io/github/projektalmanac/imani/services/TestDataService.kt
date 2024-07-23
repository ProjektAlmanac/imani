package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.entities.Figura
import io.github.projektalmanac.imani.entities.Paciente
import io.github.projektalmanac.imani.entities.Prescripcion
import io.github.projektalmanac.imani.repositories.PacienteRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class TestDataService(val pacienteRepository: PacienteRepository) {
    fun generarDatos() {
        val paciente1 = Paciente(1, "Alan", "Turing", LocalDate.of(1912, 6, 23), 1.70f, 60f, "asdf")
        pacienteRepository.save(paciente1)

        val prescripcion1 = Prescripcion(1, "Paracetamol", 2, "Prueba 1", LocalDateTime.of(2021, 6, 23, 0, 0), 5, null, Figura.CUADRADO, 1f, 1f, paciente1, null)
        val prescripcion2 = Prescripcion(2, "Ibuprofeno", 3, "Prueba 2", LocalDateTime.of(2021, 6, 23, 0, 0), 5, null, Figura.CIRCULO, 1f, 1f, paciente1, null)

        paciente1.prescripciones.add(prescripcion1)
        paciente1.prescripciones.add(prescripcion2)
        pacienteRepository.save(paciente1)
    }
}
