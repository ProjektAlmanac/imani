import { Component, OnInit } from '@angular/core';
import {IonicModule} from "@ionic/angular";
import {DatePipe, NgForOf} from "@angular/common";
import {PrescriptionsService} from "../../services/prescriptions.service";
import {Prescripcion} from "../../../generated/openapi";
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import {addIcons} from "ionicons";
import {logoIonic} from "ionicons/icons";
import { FiguraService } from 'src/app/services/figura.service';


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

  constructor(private prescriptions: PrescriptionsService,
              public figuraService: FiguraService) {
    addIcons({logoIonic})
  }

  ngOnInit() {
    this.getPrescriptions();
  }

  public toggleAccordion(idMedicamento: number) {
    this.openAccordion = this.openAccordion === idMedicamento ? null : idMedicamento;
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
