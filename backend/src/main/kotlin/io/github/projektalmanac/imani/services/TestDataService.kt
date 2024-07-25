package io.github.projektalmanac.imani.services

import com.github.javafaker.Faker
import io.github.projektalmanac.imani.entities.Figura
import io.github.projektalmanac.imani.entities.Doctor
import io.github.projektalmanac.imani.entities.Paciente
import io.github.projektalmanac.imani.entities.Prescripcion
import io.github.projektalmanac.imani.repositories.DoctorRepository
import io.github.projektalmanac.imani.repositories.PacienteRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

@Service
class TestDataService(
    val pacienteRepository: PacienteRepository,
    val doctorRepository: DoctorRepository,
    val faker: Faker
) {
    fun generarDatos() {
        val paciente1 = Paciente(1, "Crecencio", "Morales Rivera", LocalDate.of(1949, 4, 19), 1.67f, 92.3f, "asdf")
        val doctor = Doctor(1, "Luis", "Perez", "luisperez", "1234", "Zaragoza", mutableListOf<Prescripcion>(), mutableListOf<Paciente>())
        val pacientes = (1..10).map { generarPaciente() }
        doctor.pacientes.addAll(pacientes)

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

        val prescripcion6 = Prescripcion(
            6,
            "Paracetamol",
            5,
            "",
            LocalDateTime.now(),
            Int.MAX_VALUE,
            null,
            Figura.SEMICIRCULO,
            1f,
            0f,
            paciente1,
            null
        )

        paciente1.prescripciones = mutableListOf(
            prescripcion1,
            prescripcion2,
            prescripcion3,
            prescripcion4,
            prescripcion5,
            prescripcion6
        )
        pacienteRepository.saveAll(pacientes)
        doctorRepository.save(doctor)
        pacienteRepository.save(paciente1)
    }

    fun generarPaciente() = Paciente(
        id = 0,
        nombre = faker.name().firstName(),
        apellidos = faker.name().lastName() + " " + faker.name().lastName(),
        fechaNacimiento = faker.date().between(Date(40, 1, 1), Date(60, 1, 1)).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
        estatura = faker.number().numberBetween(150, 180).toFloat() / 100f,
        peso = faker.number().numberBetween(500, 800).toFloat() / 10f,
        token = "",
    )
}
