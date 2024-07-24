import {Injectable, WritableSignal} from '@angular/core';
import {
  PacienteService as PacienteApi,
} from '../../generated/openapi';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PacienteService {

  constructor(private pacienteApi: PacienteApi) {}

  public obtenerPaciente(pacienteId: number): Promise<any> {
    return lastValueFrom(this.pacienteApi.getPacienteID(pacienteId));
  }

}
