package io.github.projektalmanac.imani.services

import io.github.projektalmanac.imani.entities.Paciente
import io.github.projektalmanac.imani.entities.Prescripcion
import io.github.projektalmanac.imani.exceptions.CuerpoDePeticionNuloException
import io.github.projektalmanac.imani.exceptions.UsuarioNoEncontradoException
import io.github.projektalmanac.imani.exceptions.ListadeMedicamentosException
import io.github.projektalmanac.imani.generated.dto.PacienteDto
import io.github.projektalmanac.imani.generated.dto.PrescripcionDto
import io.github.projektalmanac.imani.generated.dto.NuevaPrescripcionDto
import io.github.projektalmanac.imani.mappers.PacienteMapper
import io.github.projektalmanac.imani.mappers.PrescripcionMapper
import io.github.projektalmanac.imani.repositories.PacienteRepository
import io.github.projektalmanac.imani.repositories.PrescripcionRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PacienteService (
    private val pacienteRepository: PacienteRepository,
    private val pacienteMapper: PacienteMapper,
    private val prescripcionMapper: PrescripcionMapper,
    private val prescripcionRepository:PrescripcionRepository
){

    fun addPaciente(pacienteDto: PacienteDto?): PacienteDto {
        if (pacienteDto === null) throw CuerpoDePeticionNuloException()
        // Mapea de PasienteDTO a la entidad Pasiente
        val paciente = pacienteMapper.toPaciente(pacienteDto)
        // Se crea un token UUID para el paciente
        paciente.token = UUID.randomUUID().toString()
        // Se persiste en base de datos la entidad
        val pacienteGuardado = pacienteRepository.save(paciente)
        return pacienteMapper.toPacienteDto(pacienteGuardado)
    }
    fun obtenerPrescripciones(idPaciente:Int):List<PrescripcionDto>{
        val paciente = pacienteRepository.findById(idPaciente)
        if (!paciente.isPresent) {
            throw UsuarioNoEncontradoException(idPaciente)
        }
        val prescripciones = prescripcionRepository.findAllByPacienteId(idPaciente)
        if(prescripciones===null)throw ListadeMedicamentosException();
        return prescripciones.map { prescripcionMapper.toPrescripcionDto(it) }
        
       
    }
    fun agregarPrescripcion( pacienteId: Int,
    nuevaListaPrescripcionDto: List<NuevaPrescripcionDto>?){
        val paciente = pacienteRepository.findById(pacienteId)
        if (!paciente.isPresent) {
            throw UsuarioNoEncontradoException(pacienteId)
        }
        if(nuevaListaPrescripcionDto===null){throw CuerpoDePeticionNuloException()}
         nuevaListaPrescripcionDto.map{

            var prescripcion = prescripcionMapper.toPrescripcion(it)
          
            prescripcionRepository.save(prescripcion);
        }

}

fun modificarPrescripcion (pacienteId: Int,
prescripcionId: Int,
nuevaPrescripcionDto: NuevaPrescripcionDto?){
    val paciente = pacienteRepository.findById(pacienteId)
    if (!paciente.isPresent) {
        throw UsuarioNoEncontradoException(pacienteId)    
    }
    if(nuevaPrescripcionDto===null){throw CuerpoDePeticionNuloException()}
   
    val Prescricpion=pacienteRepository.findById(prescripcionId)
    if(!Prescricpion.isPresent)throw UsuarioNoEncontradoException(prescripcionId)
    var nuevaPescripcion=prescripcionMapper.toPrescripcion(nuevaPrescripcionDto)
    prescripcionRepository.deleteById(prescripcionId)
    prescripcionRepository.save(nuevaPescripcion)
}

