import { Component, OnInit } from '@angular/core';
import {IonicModule} from "@ionic/angular";
import {DatePipe, NgForOf} from "@angular/common";
import {PrescriptionsService} from "../../services/prescriptions.service";
import {Prescripcion} from "../../../generated/openapi";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import {addIcons} from "ionicons";
import {logoIonic} from "ionicons/icons";


@Component({
  selector: 'app-prescriptions',
  templateUrl: './prescriptions.component.html',
  styleUrls: ['./prescriptions.component.scss'],
  imports: [
    IonicModule,
    NgForOf,
    NgxDatatableModule,
    DatePipe,

  ],
  standalone: true
})
export class PrescriptionsComponent  implements OnInit {

  public patientPrescriptions: Prescripcion[] = [];
  openAccordion: string | null = null;

  constructor(private prescriptions: PrescriptionsService) {
    addIcons({logoIonic})
  }

  ngOnInit() {
    this.getPrescriptions();
  }
  public getIcon(precipcion: Prescripcion): string {
    if(precipcion.figura === 'cuadrado'){
      return 'square-sharp'
    }
    if(precipcion.figura === 'circulo'){
      return 'ellipse-sharp'
    }
    if(precipcion.figura === 'triangulo'){
      return 'triangle-sharp'
    }
    if(precipcion.figura === 'estrella'){
      return 'star-sharp'
    }
    if(precipcion.figura === 'anillo'){
      return 'radio-button-off-sharp'
    }
    if(precipcion.figura === 'semicirculo'){
      return 'contrast-sharp'
    }
    if (precipcion.figura === 'nube'){
      return 'cloud-sharp'
    }
    return '';
  }
  public toggleAccordion(medicamento: string) {
    this.openAccordion = this.openAccordion === medicamento ? null : medicamento;
  }

  private getPrescriptions() {
    this.prescriptions.getPrescriptions()
      .then((prescriptions) => {
        this.patientPrescriptions = prescriptions;
      })
      .catch((error) => {
        console.error('Error al obtener las prescripciones:', error);
      });
  }


}
