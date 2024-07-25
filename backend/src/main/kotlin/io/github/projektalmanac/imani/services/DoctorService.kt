package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.entities.Doctor
import io.github.projektalmanac.imani.entities.Paciente
import io.github.projektalmanac.imani.exceptions.*
import io.github.projektalmanac.imani.generated.dto.DoctorDto
import io.github.projektalmanac.imani.generated.dto.NuevoDoctorDto
import io.github.projektalmanac.imani.mappers.DoctorMapper
import io.github.projektalmanac.imani.repositories.DoctorRepository
import io.github.projektalmanac.imani.repositories.FarmaceuticoRepository
import io.github.projektalmanac.imani.repositories.PacienteRepository
import org.springframework.data.repository.findByIdOrNull
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

        doctor.pacienteAtendido = paciente
        doctorRepository.save(doctor)
    }
    fun getDoctorById(idDoctor: Int): DoctorDto {
        val doctor =
                doctorRepository.findById(idDoctor).orElseThrow {
                    UsuarioNoEncontradoException(idDoctor)
                }
        return doctorMapper.toDoctorDto(doctor)
    }

    fun updateDoctor(doctorId: Int, doctorDto: DoctorDto?) {
        if (doctorDto === null) throw CuerpoDePeticionNuloException()
        val doctor = doctorRepository.findById(doctorId).orElseThrow { DoctorNotFoundException(doctorId) }
        val paciente = doctorDto.idPaciente?.let { pacienteRepository.findById(it).orElseThrow { PacienteNotFoundException(it) } }
        if (paciente !== null && !doctor.pacientes.contains(paciente))
            doctor.pacientes.add(paciente)
        doctorMapper.update(doctor, doctorDto)
        doctor.pacienteAtendido = paciente
        doctorRepository.save(doctor)
    }
}
