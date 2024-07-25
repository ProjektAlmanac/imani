import { Injectable, signal } from '@angular/core';
import {
  PacienteService as PacienteApi,
  Paciente,
} from '../../generated/openapi';
import { lastValueFrom } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class PacienteService {
  public readonly pacienteActual = signal<Paciente | undefined>(undefined);

  constructor(private pacienteApi: PacienteApi) {}

  public actualizarPaciente(pacienteId: number, paciente: Paciente) {
    return lastValueFrom(
      this.pacienteApi.upDatePacienteID(pacienteId, paciente)
    );
  }

  public obtenerPaciente(pacienteId: number) {
    return lastValueFrom(this.pacienteApi.getPacienteID(pacienteId));
  }

  public getPacientes(doctorId: number) {
    return lastValueFrom(this.pacienteApi.getPacientesDoctorID(doctorId));
  }
}
