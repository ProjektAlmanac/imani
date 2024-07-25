package io.github.projektalmanac.imani.controllers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.github.projektalmanac.imani.generated.api.PacienteApi
import io.github.projektalmanac.imani.generated.dto.PacienteDto
import io.github.projektalmanac.imani.generated.dto.PostPacientesSendQrRequestDto
import io.github.projektalmanac.imani.services.DoctorService
import io.github.projektalmanac.imani.services.PacienteService
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PacienteController(val pacienteService: PacienteService, val doctorService: DoctorService) :
        PacienteApi {
    override fun getPacienteID(pacienteId: Int): ResponseEntity<PacienteDto> {
        val paciente = pacienteService.getPaciente(pacienteId)
        return ResponseEntity.ok(paciente)
    }

    override fun getPacientesDoctorID(doctorId: Int): ResponseEntity<List<PacienteDto>> {
        val pacientes = pacienteService.getPacientes(doctorId)
        return ResponseEntity.ok(pacientes)
    }

    override fun postPacientes(pacienteDto: PacienteDto?): ResponseEntity<PacienteDto> {
        val paciente = pacienteService.addPaciente(pacienteDto)
        return ResponseEntity.ok(paciente)
    }

    override fun upDatePacienteID(
            pacienteId: Int,
            pacienteDto: PacienteDto?
    ): ResponseEntity<Unit> {
        TODO()
    }

    override fun postPacientesSendQr(
            pacienteId: Int,
            postPacientesSendQrRequestDto: PostPacientesSendQrRequestDto?
    ): ResponseEntity<Unit> {
        val docBase64 = postPacientesSendQrRequestDto?.archivo
        val reqId = 1
        if (docBase64 == null) {
            return ResponseEntity.badRequest().build()
        }

        val apiToken = "9c23aacca7676bc0a079b1e01cd1fb1a"
        val url = URL("https://ping.arya.ai/api/v1/qr")
        val connection = url.openConnection() as HttpURLConnection
        try {
            // Configurar las propiedades de la conexión
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("token", apiToken)
            connection.doOutput = true

            // Crear el cuerpo de la solicitud
            val jsonInputString =
                    """
                {
                  "doc_base64": "$docBase64",
                  "req_id": "$reqId"
                }
            """.trimIndent()

            // Enviar la solicitud
            var os: OutputStream? = null
            try {
                os = connection.outputStream
                val input = jsonInputString.toByteArray(Charsets.UTF_8)
                os.write(input, 0, input.size)
            } finally {
                os?.close()
            }

            // Leer la respuesta
            var `is`: InputStream? = null
            var response: String = ""
            try {
                `is` = connection.inputStream
                val bytes = `is`.readBytes()
                response = bytes.toString(Charsets.UTF_8)
            } finally {
                `is`?.close()
            }

            // Parsear la respuesta JSON
            val mapper = jacksonObjectMapper()
            val responseMap: Map<String, Any> = mapper.readValue(response)
            println(response)
            val data = responseMap["data"] as? Map<*, *>
            val code1 = data?.get("code1") as? Map<*, *>
            val qrData = code1?.get("qr_data") as? String

            if (qrData != null) {
                val qrDataMap: Map<String, Any> = mapper.readValue(qrData)
                val idUser = qrDataMap["idUser"] as? Int
                val isDoctor = qrDataMap["isDoctor"] as? Boolean

                if (idUser != null && isDoctor != null) {
                    println("idUser: $idUser, isDoctor: $isDoctor")
                } else {
                    return ResponseEntity.status(HttpURLConnection.HTTP_INTERNAL_ERROR).body(Unit)
                }
                this.doctorService.agregarPacienteAdoctor(idUser, pacienteId)
            } else {
                return ResponseEntity.status(HttpURLConnection.HTTP_INTERNAL_ERROR).body(Unit)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build()
        } finally {
            connection.disconnect()
        }
        return ResponseEntity.ok().build()
    }
}
