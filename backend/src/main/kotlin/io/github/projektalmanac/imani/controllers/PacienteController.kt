package io.github.projektalmanac.imani.controllers

import io.github.projektalmanac.imani.generated.api.PacienteApi
import io.github.projektalmanac.imani.generated.dto.PacienteDto
import io.github.projektalmanac.imani.generated.dto.PostPacientesSendQrRequestDto
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
class PacienteController(val pacienteService: PacienteService) : PacienteApi {

    override fun getPacienteID(pacienteId: Int): ResponseEntity<PacienteDto> {
        TODO("Not yet implemented")
    }



    override fun postPacientes(pacienteDto: PacienteDto?): ResponseEntity<PacienteDto> {
        val paciente = pacienteService.addPaciente(pacienteDto)
        return ResponseEntity.ok(paciente)
    }

    override fun upDatePacienteID(
            pacienteId: Int,
            pacienteDto: PacienteDto?
    ): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }

    override fun getPacientesDoctorID(doctorId: Int): ResponseEntity<List<PacienteDto>> {
    override fun upDatePacienteID(pacienteId: Int, pacienteDto: PacienteDto?): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }

    override fun getPacientesDoctorID(doctorId: Int): ResponseEntity<List<PacienteDto>> {
        TODO("Not yet implemented")
    }

    override fun postPacientesSendQr(
            pacienteId: Int,
            postPacientesSendQrRequestDto: PostPacientesSendQrRequestDto?
    ): ResponseEntity<Unit> {
        val docBase64 = postPacientesSendQrRequestDto?.archivo
        val reqId = 1
        if (docBase64 == null || reqId == null) {
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
            var response: String? = null
            try {
                `is` = connection.inputStream
                val bytes = `is`.readBytes()
                response = bytes.toString(Charsets.UTF_8)
            } finally {
                `is`?.close()
            }

            println("POST /api/pacientes/send-qr response: $response")
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.status(HttpURLConnection.HTTP_INTERNAL_ERROR).build()
        } finally {
            connection.disconnect()
        }
        return ResponseEntity.ok().build()
    }
     }
}
