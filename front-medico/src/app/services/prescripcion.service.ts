import {Injectable, WritableSignal} from '@angular/core';
import {
  PrescripcionesService as PrescripcionApi,
  NuevaPrescripcion,
} from '../../generated/openapi';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PrescripcionService {
  constructor(private prescripcionApi: PrescripcionApi) {}

  public crearPrescripcion(pacienteId: number, nuevaPrescripcion: NuevaPrescripcion[]): Promise<any> {
    return lastValueFrom(this.prescripcionApi.postPrescription(pacienteId, nuevaPrescripcion));
  }

  public obtenerPrescripciones(pacienteId: number): Promise<any> {
    return lastValueFrom(this.prescripcionApi.getPrescription(pacienteId));
  }

  public actualizarprescripcion(pacienteId: number, prescripcionId: number, prescripcion: NuevaPrescripcion) {
    return lastValueFrom(this.prescripcionApi.putPrescripcion(pacienteId, prescripcionId, prescripcion))
  }
}
