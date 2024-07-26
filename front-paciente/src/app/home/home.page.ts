import { CommonModule } from '@angular/common';
import { Component, inject, signal } from '@angular/core';
import { RouterModule } from '@angular/router';
import {IonicModule} from "@ionic/angular"
import {
  IonButton,
  IonContent,
  IonHeader,
  IonList,
  IonRefresher,
  IonRefresherContent,
  IonTitle,
  IonToolbar,
  RefresherCustomEvent,
  IonInput } from '@ionic/angular/standalone';
import { MessageComponent } from '../message/message.component';

import { Camera, CameraResultType, CameraSource } from '@capacitor/camera';
import { DataService, Message } from '../services/data.service';
import { QrService } from '../services/qr.service';
import { PacienteService } from '../services/paciente.service';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: true,
  imports: [
    IonicModule,
    CommonModule,
    RouterModule,
  ],
})
export class HomePage {
  private data = inject(DataService);
  public imageSrc: string = '';
  public qrResult: string = '';

  userId = signal(this.pacienteService.obtenerPaciente()?.id)

  constructor(private qrService: QrService, private pacienteService: PacienteService) {}

  async takePicture() {
    try {
      const image = await Camera.getPhoto({
        quality: 90,
        allowEditing: false,
        resultType: CameraResultType.DataUrl,
        source: CameraSource.Camera,
      });

      if (image && image.dataUrl) {
        this.imageSrc = image.dataUrl;
        console.log('Image captured:', this.imageSrc);
        const blob = await this.dataUrlToBlob(this.imageSrc);
        await this.scanQrCode(blob);
      } else {
        console.error('No image data received');
        this.qrResult = 'No image data received';
      }
    } catch (error) {
      console.error('Error taking picture', error);
      this.qrResult = 'Error taking picture';
    }
  }

  async scanQrCode(imageBlob: Blob): Promise<void> {
    try {
      await this.qrService.scanQrCode(imageBlob);
    } catch (error) {
      console.error('Error scanning QR code:', error);
      this.qrResult = 'Error scanning QR code';
    }
  }

  dataUrlToBlob(dataUrl: string): Promise<Blob> {
    return fetch(dataUrl).then((res) => res.blob());
  }

  refresh(ev: any) {
    setTimeout(() => {
      (ev as RefresherCustomEvent).detail.complete();
    }, 3000);
  }

  getMessages(): Message[] {
    return this.data.getMessages();
  }

  update() {
    const id = this.userId()
    if (id == undefined || isNaN(id) || id == 0)
      this.pacienteService.guardarPaciente(null!)
    else
      this.pacienteService.guardarPaciente({ id } as any)
  }

  setValue(event: Event) {
    const target = event.target as HTMLInputElement
    this.userId.set(+target.value)
  }
}
