import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Camera, CameraResultType, CameraSource } from '@capacitor/camera';
import {
  IonContent,
  IonHeader,
  IonTitle,
  IonToolbar,
} from '@ionic/angular/standalone';
import { NgxScannerQrcodeModule } from 'ngx-scanner-qrcode';
import { QrService } from 'src/app/services/qr.service';

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
  ],
})
export class LeerQrPage {
  imageSrc: string | null = null;
  qrResult: string | null = null;
  errorMessage: string | null = null;

  constructor(private qrService: QrService) {}

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
}
