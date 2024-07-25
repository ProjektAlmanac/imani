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
        val paciente1 = Paciente(1, "Crecencio", "Morales Rivera", LocalDate.of(1949, 4, 19), 1.67f, 92.3f, "asdf")
        pacienteRepository.save(paciente1)

        val prescripcion1 = Prescripcion(
            1, "Atorvastatina",
            86400, // 1 día en segundos
            "Tomar con el desayuno",
            LocalDateTime.of(2024, 7, 25, 8, 0),
            30 * 86400, // 30 días en segundos
            null,
            Figura.CUADRADO,
            10f,
            0f,
            paciente1,
            null
        )

        val prescripcion2 = Prescripcion(
            2, "Metformina",
            2 * 86400, // 2 dias en segundos
            "Tomar con el desayuno y la cena",
            LocalDateTime.of(2024, 7, 24, 18, 0),
            60 * 86400, // 60 días en segundos
            null,
            Figura.CIRCULO,
            500f,
            0f,
            paciente1,
            null
        )

        val prescripcion3 = Prescripcion(
            3, "Lisinopril",
            86400, // 1 día en segundos
            "Tomar a la misma hora todos los días",
            LocalDateTime.of(2024, 7, 25, 9, 0),
            15 * 86400, // 15 días en segundos
            null,
            Figura.TRIANGULO,
            20f,
            0f,
            paciente1,
            null
        )

        val prescripcion4 = Prescripcion(
            4, "Alendronato",
            43200, // 12 horas en segundos
            "Tomar con un vaso lleno de agua al despertar, esperar al menos 30 minutos antes de comer o beber",
            LocalDateTime.of(2024, 7, 24, 8, 30),
            13 * 86400, // 12 semanas en segundos
            null,
            Figura.ESTRELLA,
            70f,
            0f,
            paciente1,
            null
        )

        val prescripcion5 = Prescripcion(
            5, "Aspirina",
            86400, // 1 día en segundos
            "Tomar después de la cena",
            LocalDateTime.of(2024, 7, 24, 20, 0),
            7 * 86400, // 7 días en segundos
            null,
            Figura.ANILLO,
            81f,
            0f,
            paciente1,
            null
        )

        paciente1.prescripciones.add(prescripcion1)
        paciente1.prescripciones.add(prescripcion2)
        paciente1.prescripciones.add(prescripcion3)
        paciente1.prescripciones.add(prescripcion4)
        paciente1.prescripciones.add(prescripcion5)
       pacienteRepository.save(paciente1)
    }
}
