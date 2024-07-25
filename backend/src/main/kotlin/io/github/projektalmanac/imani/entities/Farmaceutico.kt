package io.github.projektalmanac.imani.entities

import jakarta.persistence.*

@Entity
class Farmaceutico(
    @Id
    @GeneratedValue
    var id: Int,
    var nombre: String,
    var apellidos: String,
    var nombreUsuario: String,
    var passwordHash: String,
    @OneToOne(fetch = FetchType.LAZY)
    var pacientaAtendido: Paciente? = null
)
