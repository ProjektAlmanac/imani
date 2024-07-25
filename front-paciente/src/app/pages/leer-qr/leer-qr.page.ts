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
import QrScanner from 'qr-scanner';

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
  ],
})
export class LeerQrPage implements OnInit {
  imageSrc: string | null = null;
  qrResult: string | null = null;

  constructor() {}
  ngOnInit(): void {}

  async takePicture() {
    try {
      const image = await Camera.getPhoto({
        quality: 90,
        allowEditing: false,
        resultType: CameraResultType.DataUrl,
        source: CameraSource.Camera,
      });

      this.imageSrc = image.dataUrl ?? null;
      console.log('Image captured:', this.imageSrc);

      if (this.imageSrc) {
        await this.waitForImageLoad(this.imageSrc);
        this.scanQrCode(this.imageSrc);
      }
    } catch (error) {
      console.error('Error taking picture', error);
      this.qrResult = 'Error taking picture';
    }
  }

  waitForImageLoad(imageDataUrl: string): Promise<void> {
    return new Promise((resolve, reject) => {
      const img = new Image();
      img.src = imageDataUrl;
      img.onload = () => {
        console.log('Image loaded successfully');
        resolve();
      };
      img.onerror = (error) => {
        console.error('Error loading image', error);
        reject(error);
      };
    });
  }

  async scanQrCode(imageDataUrl: string) {
    try {
      const img = new Image();
      img.src = imageDataUrl;

      img.onload = async () => {
        try {
          console.log('Starting QR code scan');
          const result = await QrScanner.scanImage(img);
          console.log('QR code result:', result);
          this.qrResult = result;
        } catch (err) {
          console.error('Error reading QR code', err);
          this.qrResult = 'Error reading QR code: ' + err;
        }
      };

      img.onerror = (error) => {
        console.error('Error loading image for scanning', error);
        this.qrResult = 'Error loading image for scanning';
      };
    } catch (error) {
      console.error('Error waiting for image load', error);
      this.qrResult = 'Error waiting for image load';
    }
  }
}
