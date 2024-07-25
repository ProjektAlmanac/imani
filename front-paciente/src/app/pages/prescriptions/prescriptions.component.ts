import { Component, OnInit } from '@angular/core';
import {IonicModule} from "@ionic/angular";
import {DatePipe, NgForOf} from "@angular/common";
import {PrescriptionsService} from "../../services/prescriptions.service";
import {Prescripcion} from "../../../generated/openapi";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import {addIcons} from "ionicons";
import {logoIonic} from "ionicons/icons";
import {ColorService} from "../../services/color.service";


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
  openAccordion: number | null = null;
  public colors: { [key: number]: string } = {};

  constructor(private prescriptions: PrescriptionsService,
              private colorService: ColorService) {
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
  public toggleAccordion(idMedicamento: number) {
    this.openAccordion = this.openAccordion === idMedicamento ? null : idMedicamento;
  }
  private getPrescriptions() {
    this.prescriptions.getPrescriptions()
      .then((prescriptions) => {
        this.patientPrescriptions = prescriptions;
        this.assignColorsToPrescriptions();
      })
      .catch((error) => {
        console.error('Error al obtener las prescripciones:', error);
      });
  }
  private assignColorsToPrescriptions() {
    this.patientPrescriptions.forEach(prescripcion => {
      this.colors[prescripcion.id] = this.colorService.getRandomColor();
    });
  }
  public getColor(id: number): string {
    return this.colors[id] || '#FFFFFF'; // Retorna el color asociado o blanco si no existe
  }


}
