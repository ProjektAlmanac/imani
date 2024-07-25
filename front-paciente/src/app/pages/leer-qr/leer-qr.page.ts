import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {
  IonContent,
  IonHeader,
  IonTitle,
  IonToolbar,
} from '@ionic/angular/standalone';
import { NgxScannerQrcodeModule } from 'ngx-scanner-qrcode';
import { Router, RouterModule } from '@angular/router';
import { Html5QrcodeScanner } from 'html5-qrcode';
import { PacienteService } from 'src/app/services/paciente.service';
import { DoctorService, FarmaceuticoService } from 'src/generated/openapi';
import { lastValueFrom } from 'rxjs';

@Component({
  selector: 'app-leer-qr',
  templateUrl: './leer-qr.page.html',
  styleUrls: ['./leer-qr.page.scss'],
  standalone: true,
  imports: [
    IonContent,
    IonHeader,
    IonTitle,
    IonToolbar,
    CommonModule,
    FormsModule,
    NgxScannerQrcodeModule,
    RouterModule
  ],
})
export class LeerQrPage implements AfterViewInit {
  scanner!: Html5QrcodeScanner;

  constructor(
    private pacienteService: PacienteService,
    private doctorService: DoctorService,
    private farmaceuticoService: FarmaceuticoService,
    private router: Router
  ) {}

  ngAfterViewInit() {
    this.scanner = new Html5QrcodeScanner(
      'qr-scanner',
      {
        fps: 10,
        qrbox: { width: 250, height: 250 },
      },
      false
    );
    this.scanner.render(this.onCodeScanned.bind(this), () => undefined);
  }

  async onCodeScanned(code: string) {
    try {
      const obj = JSON.parse(code);
      if (obj.idUser == undefined || obj.isDoctor == undefined) {
        return;
      }
      this.router.navigate(["/"])
      this.scanner.pause();
      const paciente = this.pacienteService.obtenerPaciente();
      if (paciente == null) return;
      if (obj.isDoctor) {
        const doctor = await lastValueFrom(this.doctorService.getDoctor(obj.idUser))
        console.log(doctor)
        await lastValueFrom(this.doctorService.putDoctor(obj.idUser, {
          ...doctor,
          idPaciente: paciente.id
        }))
      } else {
        const farmaceutico = await lastValueFrom(this.farmaceuticoService.getFarmaceutico(obj.idUser))
        await lastValueFrom(this.farmaceuticoService.putFarmaceuticoFarmaceuticoId(obj.idUser, {
          ...farmaceutico,
          idPaciente: paciente.id
        }))
      }
    } catch (error) {}
  }
}
