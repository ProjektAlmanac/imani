package io.github.projektalmanac.imani.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Cita(
    @Id
    @GeneratedValue
    var id: Int,
    var fecha: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    var paciente: Paciente,

    @ManyToOne(fetch = FetchType.LAZY)
    var doctor: Doctor,
)
