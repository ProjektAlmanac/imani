package io.github.projektalmanac.imani.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Farmaceutico(
    @Id
    @GeneratedValue
    var id: Int,
    var nombre: String,
    var apellidos: String,
    var nombreUsuario: String,
    var passwordHash: String
)
