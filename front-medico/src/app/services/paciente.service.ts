import {Injectable} from '@angular/core';
import {
  PacienteService as PacienteApi,
  Paciente,
} from '../../generated/openapi';
import { lastValueFrom } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class PacienteService {
  constructor(private pacienteApi: PacienteApi) {}

  public actualizarPaciente(pacienteId: number, paciente: Paciente): Promise<any> {
    return lastValueFrom(this.pacienteApi.upDatePacienteID(pacienteId, paciente));
  }

  public obtenerPaciente(pacienteId: number): Promise<any> {
    return lastValueFrom(this.pacienteApi.getPacienteID(pacienteId));
  }
}
