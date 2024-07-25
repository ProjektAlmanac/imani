package io.github.projektalmanac.imani.entities

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Paciente(
    @Id
    @GeneratedValue
    var id: Int,
    var nombre: String?,
    var apellidos: String?,
    var fechaNacimiento: LocalDate?,
    var estatura: Float?,
    var peso: Float?,
    var token: String?,
) {
    @OneToMany(mappedBy = "paciente", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var prescripciones: MutableList<Prescripcion> = mutableListOf()

    @ManyToMany(mappedBy = "pacientes", fetch = FetchType.LAZY)
    var doctores: MutableList<Doctor> = mutableListOf()

    @OneToMany(mappedBy = "paciente", cascade = [CascadeType.ALL] ,fetch = FetchType.LAZY)
    var citas: MutableList<Cita> = mutableListOf()
}
