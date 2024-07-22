import { Component, OnInit } from '@angular/core';
import {PacienteService} from "../../services/paciente.service";
import {IonicModule, ToastController} from "@ionic/angular";
import {NgOptimizedImage} from "@angular/common";
import {Paciente} from "../../../generated/openapi";

@Component({
  selector: 'app-sigup-patient',
  templateUrl: './sigup-patient.component.html',
  styleUrls: ['./sigup-patient.component.scss'],
  imports: [
    IonicModule,
    NgOptimizedImage
  ],
  standalone: true
})

export class SigupPatientComponent implements OnInit{


  constructor(private pacienteService: PacienteService,
              private notificacion: ToastController) { }

  ngOnInit(): void {
    this.generaCuentaPaciente();
  }


  private async generaCuentaPaciente(): Promise<void> {
    await this.pacienteService.creaPaciente({ id: 0 });
  }




}
