import { Component, OnInit } from '@angular/core';
import { IonApp, IonRouterOutlet } from '@ionic/angular/standalone';
import { NotificacionService } from './services/notificacion.service';
import { PrescripcionService } from './services/prescripcion.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  standalone: true,
  imports: [IonApp, IonRouterOutlet],
})
export class AppComponent {
  constructor(prescripcionService: PrescripcionService, notificacionService: NotificacionService) {
    prescripcionService.start()
    notificacionService.start()
  }
}
