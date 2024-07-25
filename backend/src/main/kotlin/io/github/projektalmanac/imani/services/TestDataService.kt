package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.entities.Doctor
import io.github.projektalmanac.imani.entities.Figura
import io.github.projektalmanac.imani.entities.Paciente
import io.github.projektalmanac.imani.entities.Prescripcion
import io.github.projektalmanac.imani.repositories.DoctorRepository
import io.github.projektalmanac.imani.repositories.PacienteRepository
import io.github.projektalmanac.imani.repositories.PrescripcionRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class TestDataService(
    val pacienteRepository: PacienteRepository,
    val prescripcionRepository: PrescripcionRepository,
    val doctorRepository: DoctorRepository
) {
    fun generarDatos() {
        val paciente1 = Paciente(1, "Alan", "Turing", LocalDate.of(1912, 6, 23), 1.70f, 60f, "asdf")
        val paciente2 = Paciente(2, "Alan", "Turing", LocalDate.of(1912, 6, 23), 1.70f, 60f, "asdf")
        val doctor = Doctor(1, "Luis", "Perez", "luisperez", "1234", "Zaragoza", mutableListOf<Prescripcion>(), mutableListOf<Paciente>())
        val prescripcion1 = Prescripcion(1, "Paracetamol", 8, "Tomar cada 8 horas", LocalDateTime.now(), 7, "1234", Figura.ANILLO, 500f, 3f, paciente2, doctor)
        val prescripcion2 = Prescripcion(2, "Ibuprofeno", 8, "Tomar cada 8 horas", LocalDateTime.now(), 7, "1234", Figura.NUBE, 500f, 3f, paciente2, doctor)
        val prescripcion3 = Prescripcion(3, "ketorolaco", 8, "Tomar cada 8 horas", LocalDateTime.now(), 7, "1234", Figura.ESTRELLA, 500f, 3f, paciente2, doctor)
        pacienteRepository.save(paciente1)
        pacienteRepository.save(paciente2)
        doctorRepository.save(doctor)
        prescripcionRepository.save(prescripcion1)
        prescripcionRepository.save(prescripcion2)
        prescripcionRepository.save(prescripcion3)
    }
}
