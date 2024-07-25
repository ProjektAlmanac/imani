import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { QRCodeModule } from 'angularx-qrcode';
import { lastValueFrom } from 'rxjs';
import { Doctor, DoctorService, Farmaceutico, FarmaceuticoService, Usuario } from '../../../generated/openapi';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-generate-qr',
  standalone: true,
  imports: [CommonModule, FormsModule, QRCodeModule],
  templateUrl: './generate-qr.component.html',
  styleUrls: ['./generate-qr.component.scss'],
})
export class GenerateQrComponent implements OnInit, OnDestroy {
  public qrData: string;
  private intervalId: any;

  constructor(
    private session: UsuarioService,
    private doctorService: DoctorService,
    private farmaceuticoService: FarmaceuticoService,
    private router: Router,
  ) {
    const idUser: number = +(this.session.getUsuario()?.id ?? 0);
    const isDoctor: boolean =
      this.session.getUsuario()?.rol == Usuario.RolEnum.Doctor;
    this.qrData = JSON.stringify({ idUser, isDoctor });
  }

  ngOnInit() {
    this.intervalId = setInterval(() => {
      this.checkAndRedirect();
    }, 400); // 0.4 segundos = 400 milisegundos
  }

  ngOnDestroy() {
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
  }

  private async checkAndRedirect() {
    const idUser: number = +(this.session.getUsuario()?.id ?? 0);
    const isDoctor: boolean =
      this.session.getUsuario()?.rol == Usuario.RolEnum.Doctor;

    try {
      const user = await this.getUsuario(idUser, isDoctor)
      if (user.idPaciente != null) {
        this.router.navigate(['/pacientes', user.idPaciente]);
      }
      await this.resetPaciente(idUser, isDoctor, user)
    } catch (error) {
      console.error('Error al obtener el doctor:', error);
    }
  }

  private async getUsuario(idUser: number, isDoctor: boolean): Promise<Doctor | Farmaceutico> {
    if (isDoctor) {
      return lastValueFrom(this.doctorService.getDoctor(idUser))
    }
    else {
      return lastValueFrom(this.farmaceuticoService.getFarmaceutico(idUser))
    }
  }

  private async resetPaciente(idUser: number, isDoctor: boolean, usuario: Doctor | Farmaceutico) {
    if (isDoctor) {
      return lastValueFrom(this.doctorService.putDoctor(idUser, {
        ...usuario as Doctor,
        idPaciente: undefined
      }))
    }
    else {
      return lastValueFrom(this.farmaceuticoService.putFarmaceuticoFarmaceuticoId(idUser, {
        ...usuario,
        idPaciente: undefined
      }))
    }
  }
}
