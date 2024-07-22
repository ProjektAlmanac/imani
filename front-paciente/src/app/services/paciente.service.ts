import { Injectable } from '@angular/core';
import {lastValueFrom} from "rxjs";
import {PacienteService as PacienteAPi, Paciente} from "../../generated/openapi";

@Injectable({
  providedIn: 'root'
})
export class PacienteService {

  constructor(private pacienteApi: PacienteAPi) { }

  public creaPaciente(paciente: Paciente) {
    return lastValueFrom(this.pacienteApi.postPacientes(paciente));
  }
}
