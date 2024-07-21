package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.entities.Paciente
import io.github.projektalmanac.imani.repositories.PacienteRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TestDataService(val pacienteRepository: PacienteRepository) {
    fun generarDatos() {
        val paciente1 = Paciente(1, "Alan", "Turing", LocalDate.of(1912, 6, 23), 1.70f, 60f, "asdf")

        pacienteRepository.save(paciente1)
    }
}
