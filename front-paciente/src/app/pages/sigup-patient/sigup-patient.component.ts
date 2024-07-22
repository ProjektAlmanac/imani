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
  async mensajeNotificacion(mensaje: string, duracion: number = 2000 , tipo: string = 'success') {
    const color = tipo === 'success' ? 'success' : 'danger';
    const toast = await this.notificacion.create({
      message: mensaje,
      duration: duracion,
      position: 'top',
      color: color,
      cssClass: 'custom-toast'
    });
    await toast.present();
  }

  private generaCuentaPaciente(): void {
    this.pacienteService
      .creaPaciente(this.creaPaciente())
      .then(() => {this.mensajeNotificacion('Cuenta creada correctamente',2000, 'success')})
      .catch(() => {this.mensajeNotificacion('Error al crear la cuenta', 2000,'danger')});
  }
  private creaPaciente(): Paciente{
    return  {
        id: 0
      };
  }



}
