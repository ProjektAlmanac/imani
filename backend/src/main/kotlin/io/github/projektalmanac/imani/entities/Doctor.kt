package io.github.projektalmanac.imani.entities

import jakarta.persistence.*

@Entity
class Doctor(
        @Id @GeneratedValue var id: Int,
        var nombre: String,
        var apellidos: String,
        var nombreUsuario: String,
        var passwordHash: String,
        var centroMedico: String,
        @OneToMany(mappedBy = "doctor", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        var prescripciones: MutableList<Prescripcion> = mutableListOf(),
        @ManyToMany(fetch = FetchType.LAZY) var pacientes: MutableList<Paciente> = mutableListOf(),
        @OneToMany(mappedBy = "doctor", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        var citas: MutableList<Cita> = mutableListOf(),
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "paciente_atendido_id")
        var pacienteAtendido: Paciente? = null
)
