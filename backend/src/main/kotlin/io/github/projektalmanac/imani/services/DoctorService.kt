package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.entities.Doctor
import io.github.projektalmanac.imani.entities.Paciente
import io.github.projektalmanac.imani.exceptions.CuerpoDePeticionNuloException
import io.github.projektalmanac.imani.exceptions.NombreUsuarioTomadoException
import io.github.projektalmanac.imani.exceptions.UsuarioNoEncontradoException
import io.github.projektalmanac.imani.generated.dto.NuevoDoctorDto
import io.github.projektalmanac.imani.mappers.DoctorMapper
import io.github.projektalmanac.imani.repositories.DoctorRepository
import io.github.projektalmanac.imani.repositories.FarmaceuticoRepository
import io.github.projektalmanac.imani.repositories.PacienteRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class DoctorService(
        private val doctorMapper: DoctorMapper,
        private val doctorRepository: DoctorRepository,
        private val farmaceuticoRepository: FarmaceuticoRepository,
        private val passwordEncoder: PasswordEncoder,
        private val pacienteRepository: PacienteRepository
) {
    fun addDoctor(nuevoDoctorDto: NuevoDoctorDto?) {
        if (nuevoDoctorDto === null) throw CuerpoDePeticionNuloException()

        val doctorExistente =
                doctorRepository.findDoctorByNombreUsuario(nuevoDoctorDto.nombreUsuario)
        if (doctorExistente !== null)
                throw NombreUsuarioTomadoException(nuevoDoctorDto.nombreUsuario)

        val farmaceuticoExistente =
                farmaceuticoRepository.findFarmaceuticoByNombreUsuario(nuevoDoctorDto.nombreUsuario)
        if (farmaceuticoExistente !== null)
                throw NombreUsuarioTomadoException(nuevoDoctorDto.nombreUsuario)

        val passwordHash = passwordEncoder.encode(nuevoDoctorDto.password)

        val doctor = doctorMapper.toDoctor(nuevoDoctorDto, passwordHash)
        doctorRepository.save(doctor)
    }
    fun agregarPacienteAdoctor(idDoctor: Int, idPaciente: Int) {
        val doctor: Doctor =
                doctorRepository.findById(idDoctor).orElseThrow {
                    UsuarioNoEncontradoException(idDoctor)
                }
        val paciente: Paciente =
                pacienteRepository.findPacienteById(idPaciente)
                        ?: throw UsuarioNoEncontradoException(idPaciente)

        if (!doctor.pacientes.contains(paciente)) {
            doctor.pacientes.add(paciente)
            paciente.doctores.add(doctor)
            doctorRepository.save(doctor)
            pacienteRepository.save(paciente)
        }
    }
}
