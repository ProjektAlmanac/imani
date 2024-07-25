import { Injectable, signal } from '@angular/core';
import { lastValueFrom, Observable } from 'rxjs';
import {
  Prescripcion,
  PrescripcionesService as PrescripcionApi,
} from 'src/generated/openapi';
import { PacienteService } from './paciente.service';

@Injectable({
  providedIn: 'root',
})
export class PrescripcionService {
  public readonly prescripciones = signal<Prescripcion[]>([]);
  public readonly cambios = signal<Prescripcion[]>([]);

  constructor(
    private prescripcionApi: PrescripcionApi,
    private pacienteService: PacienteService
  ) {
  }

  public start() {
    this.getMedicamentos();
    setInterval(this.getMedicamentos, 2000);
  }

  public getPrescripciones(pacienteId: number) {
    return lastValueFrom(this.prescripcionApi.getPrescription(pacienteId));
  }

  public getPrescripcion(prescripcionId: number) {
    return this.prescripciones().find((p) => p.id == prescripcionId);
  }

  private getMedicamentos = async () => {
    const paciente = this.pacienteService.obtenerPaciente();
    if (paciente === null) return;
    const prescripciones = await this.getPrescripciones(paciente.id);

    const cambios = this.getCambios(this.prescripciones(), prescripciones);
    this.prescripciones.set(prescripciones)

    if (cambios.length !== 0) {
      this.cambios.set(cambios);
    }
  };

  /**
   * Encuentra las prescripciones cuyo intervalo, duración o fecha de inicio han cambiado,
   * así como prescripciones que no estén en la lista vieja
   * @param prescripcionesViejas La lista de prescripciones que ya se tiene
   * @param prescripcionesNuevas La nueva lista de prescripciones
   */
  private getCambios(
    prescripcionesViejas: Prescripcion[],
    prescripcionesNuevas: Prescripcion[]
  ) {
    const cambios: Prescripcion[] = [];

    for (const prescripcionNueva of prescripcionesNuevas) {
      const id = prescripcionNueva.id;
      const prescripcionVieja = prescripcionesViejas.find((p) => p.id == id);

      if (prescripcionVieja === undefined) {
        // Es une prescripcion completamente nueva
        cambios.push(prescripcionNueva);
        continue;
      }

      if (
        prescripcionNueva.duracion !== prescripcionVieja.duracion ||
        prescripcionNueva.frecuenciaDosis !==
          prescripcionVieja.frecuenciaDosis ||
        prescripcionNueva.inicio !== prescripcionVieja.inicio
      ) {
        cambios.push(prescripcionNueva);
        continue;
      }
    }

    return cambios;
  }
}
