import { Component, computed, Signal, signal } from '@angular/core';
import { IonicModule } from '@ionic/angular';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { Prescripcion } from 'src/generated/openapi';
import { PrescripcionService } from 'src/app/services/prescripcion.service';
import { Location } from '@angular/common';
import { FiguraService } from 'src/app/services/figura.service';
import { NotificacionService } from 'src/app/services/notificacion.service';
import { PacienteService } from 'src/app/services/paciente.service';

@Component({
  selector: 'app-notificacion-medicamento',
  templateUrl: './notificacion-medicamento.component.html',
  styleUrls: ['./notificacion-medicamento.component.scss'],
  imports: [IonicModule, RouterModule],
  standalone: true,
})
export class NotificacionMedicamentoComponent {
  prescripcionId = signal<number | undefined>(undefined);
  prescripcion: Signal<Prescripcion | undefined>;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    public figuraService: FiguraService,
    private notificacionService: NotificacionService,
    private prescripcionService: PrescripcionService,
    private pacienteService: PacienteService
  ) {
    this.route.paramMap.subscribe((paramMap) => {
      const idStr = paramMap.get('id');
      if (idStr == undefined) {
        this.prescripcionId.set(undefined);
        return;
      }
      this.prescripcionId.set(Number(idStr));
    });

    this.prescripcion = computed(() =>
      prescripcionService
        .prescripciones()
        .find((p) => p.id == this.prescripcionId())
    );
  }

  public regresar() {
    this.location.back();
  }

  public posponer() {
    setTimeout(
      () => this.notificacionService.notificarMedicamento(this.prescripcion()!),
      5 * 60 * 1000
    );
    this.location.back()
    const pacienteId = this.pacienteService.obtenerPaciente()?.id
    const prescripcion = this.prescripcion()
    if (pacienteId == undefined) return
    if (prescripcion == undefined) return
    this.prescripcionService.updatePrescripcion(pacienteId, {
      ...prescripcion,
      numeroDeDosis: prescripcion.numeroDeDosis - prescripcion.cantidadPorDosis
    })
  }
}
