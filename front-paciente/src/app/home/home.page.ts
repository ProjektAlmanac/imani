import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { RouterModule } from '@angular/router';
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
} from '@ionic/angular/standalone';
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
    IonButton,
    CommonModule,
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    IonRefresher,
    IonRefresherContent,
    IonList,
    MessageComponent,
    RouterModule,
  ],
})
export class HomePage {
  private data = inject(DataService);
  constructor(private qrService: QrService, private pacienteService: PacienteService) {}
  public imageSrc: string = '';
  public qrResult: string = '';
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

  reset() {
    this.pacienteService.guardarPaciente(null!);
  }
}
