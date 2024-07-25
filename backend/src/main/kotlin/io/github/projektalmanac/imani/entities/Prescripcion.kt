package io.github.projektalmanac.imani.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Prescripcion(
    @Id
    @GeneratedValue
    var id: Int,
    var medicamento: String,
    var frecuenciaDosis: Int,
    var indicaciones: String,
    var inicio: LocalDateTime?,
    var duracion: Int,
    var identificador: String?,
    var figura: Figura,
    var cantidadPorDosis: Float,
    var numeroDeDosis: Float,

    @ManyToOne(fetch = FetchType.LAZY)
    var paciente: Paciente,

    @ManyToOne(fetch = FetchType.LAZY)
    var doctor: Doctor
)

enum class Figura {
    CUADRADO,
    CIRCULO,
    TRIANGULO,
    ESTRELLA,
    SEMICIRCULO,
    NUBE,
    ANILLO
}
