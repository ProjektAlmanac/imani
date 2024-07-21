package io.github.projektalmanac.imani.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    var id: Int,
    var firstName: String,
    var lastName: String,
    var email: String,
    var dateOfBirth: LocalDate,
    var emailVerified: Boolean,
    var createDate: LocalDate
)
