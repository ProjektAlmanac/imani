import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { QRCodeModule } from 'angularx-qrcode';
import { lastValueFrom } from 'rxjs';
import { DoctorService, Usuario } from '../../../generated/openapi';
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

    if (!isDoctor) {
      console.error('El usuario no es un médico');
      return;
    }

    try {
      const doctor = await lastValueFrom(this.doctorService.getDoctor(idUser));
      if (doctor.idPaciente != null) {
        this.router.navigate([`/pacientes/${doctor.idPaciente}`]);
      }
    } catch (error) {
      console.error('Error al obtener el doctor:', error);
    }
  }
}
