import { effect, Injectable } from '@angular/core';
import { PrescripcionService } from './prescripcion.service';
import { PacienteService } from './paciente.service';
import { Prescripcion } from 'src/generated/openapi';
import { Router } from '@angular/router';
const prescripcionesPrueba: Prescripcion[] = [{
  cantidadPorDosis: 0,
  duracion: Number.MAX_SAFE_INTEGER,
  figura: "anillo",
  frecuenciaDosis: 5,
  id: 1,
  indicaciones: "",
  medicamento: "",
  numeroDeDosis: 0,
  idDoctor: 0,
  identificador: "",
  inicio: new Date().toISOString()
}]
@Injectable({
  providedIn: 'root',
})
export class NotificacionService {
  private timeouts = new Map<number, number>();

  constructor(
    private prescripcionService: PrescripcionService,
    private router: Router,
  ) {

  }

  public start() {
      this.actualizarProgramacion(this.prescripcionService.prescripciones())
    effect(() => {
      console.log("Cambios", this.prescripcionService.cambios())
      this.actualizarProgramacion(this.prescripcionService.cambios())
    })
  }

  private actualizarProgramacion(prescripciones: Prescripcion[]) {
    for (const prescripcion of prescripciones) {
      let timeout = this.timeouts.get(prescripcion.id)

      if (timeout != undefined) {
        clearTimeout(timeout);
      }

      const tiempoParaSiguienteToma = this.calcularTiempoParaSiguienteToma(prescripcion, Date.now());

      if (tiempoParaSiguienteToma == undefined) continue;

      timeout = setTimeout(() => this.notificarMedicamento(prescripcion), tiempoParaSiguienteToma) as unknown as number
      this.timeouts.set(prescripcion.id, timeout)
    }
  }

  public notificarMedicamento = (prescripcion: Prescripcion) => {
    if (prescripcion.inicio == undefined) {
      clearTimeout(this.timeouts.get(prescripcion.id));
      return;
    }
    else if (Date.now() >= (new Date(prescripcion.inicio).valueOf() + (prescripcion.duracion * 1000))) {
      clearTimeout(this.timeouts.get(prescripcion.id));
      return;
    }

    const timeout = setTimeout(() => this.notificarMedicamento(prescripcion), prescripcion.frecuenciaDosis * 1000) as unknown as number;

    this.timeouts.set(prescripcion.id, timeout)

    this.router.navigate(["/notificacion-medicamento/", prescripcion.id])
  }

  public calcularTiempoParaSiguienteToma(prescripcion: Prescripcion, fechaActual: number) {
    if (prescripcion.inicio == undefined) return undefined;
    const fechaInicio = new Date(prescripcion.inicio).valueOf()
    if (fechaActual > fechaInicio + prescripcion.duracion * 1000) {
      return undefined;
    }
    const frecuenciaMilis = prescripcion.frecuenciaDosis * 1000
    const diferenciaTiempo = fechaActual - fechaInicio
    return frecuenciaMilis - diferenciaTiempo % frecuenciaMilis
  }
}
