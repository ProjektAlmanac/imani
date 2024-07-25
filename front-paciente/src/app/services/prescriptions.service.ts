import { Injectable } from '@angular/core';
import {Paciente, PrescripcionesService as PrescripcionApi} from "../../generated/openapi";
import {lastValueFrom} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PrescriptionsService {

  private llaveStorage = 'paciente';

  constructor(private prescricionService: PrescripcionApi) { }

  public getPrescriptions() {
    const paciente = this.obtenerPaciente();
    if (paciente) {
      return lastValueFrom(this.prescricionService.getPrescription(paciente.id));
    }
    return Promise.resolve([]);
  }

  private obtenerPaciente(): Paciente | null {
    const paciente = localStorage.getItem(this.llaveStorage);
    return paciente ? JSON.parse(paciente) : null;
  }
}
