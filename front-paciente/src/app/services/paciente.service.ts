import { Injectable } from '@angular/core';
import {lastValueFrom} from "rxjs";
import {PacienteService as PacienteAPi, Paciente} from "../../generated/openapi";

@Injectable({
  providedIn: 'root'
})
export class PacienteService {

  private llaveStorage = 'paciente';
  constructor(private pacienteApi: PacienteAPi) { }


  public async creaPaciente(paciente: Paciente): Promise<Paciente> {
    const pacienteGuardado = this.obtenerPaciente();
    if (pacienteGuardado) {
      return pacienteGuardado;
    }

    try {
      const nuevoPaciente = await lastValueFrom(this.pacienteApi.postPacientes(paciente));
      this.guardarPaciente(nuevoPaciente);
      return nuevoPaciente;
    } catch (error) {
      console.error('Error al crear el paciente:', error);
      throw error;
    }
  }

  private guardarPaciente(paciente: Paciente): void {
    localStorage.setItem(this.llaveStorage, JSON.stringify(paciente));
  }

  private obtenerPaciente(): Paciente | null {
    const paciente = localStorage.getItem(this.llaveStorage);
    return paciente ? JSON.parse(paciente) : null;
  }
}
