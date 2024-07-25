import { Component, OnInit } from '@angular/core';
import { IonApp, IonRouterOutlet } from '@ionic/angular/standalone';
import { NotificacionService } from './services/notificacion.service';
import { PrescripcionService } from './services/prescripcion.service';
import { PacienteService } from './services/paciente.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  standalone: true,
  imports: [IonApp, IonRouterOutlet],
})
export class AppComponent implements OnInit {
  constructor(prescripcionService: PrescripcionService, notificacionService: NotificacionService, private pacienteService: PacienteService) {
    prescripcionService.start()
    notificacionService.start()
  }

  ngOnInit() {
    this.pacienteService.creaPaciente()
  }
}
