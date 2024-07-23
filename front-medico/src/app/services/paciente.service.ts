import {Injectable} from "@angular/core";
import {
  Paciente,
  PacienteService as PacienteApi,
} from '../../generated/openapi';
import { lastValueFrom } from 'rxjs';
Injectable({
  providedIn: 'root',
})
export class PacienteService {
  constructor(private pacienteApi: PacienteApi) {}

  public actualizarPaciente(pacienteId: number, paciente: Paciente): Promise<any> {
    return lastValueFrom(this.pacienteApi.upDatePacienteID(pacienteId, paciente));
  }
}
