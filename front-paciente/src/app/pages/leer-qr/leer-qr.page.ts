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
import jsQR from 'jsqr';
import { NgxScannerQrcodeModule } from 'ngx-scanner-qrcode';

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

      if (image && image.dataUrl) {
        this.imageSrc = image.dataUrl;
        console.log('Image captured:', this.imageSrc);

        await this.waitForImageLoad(this.imageSrc);
        await this.scanQrCode(this.imageSrc);
      } else {
        console.error('No image data received');
        this.qrResult = 'No image data received';
      }
    } catch (error) {
      console.error('Error taking picture', error);
      this.qrResult = 'Error taking picture';
    }
  }

  async waitForImageLoad(imageDataUrl: string): Promise<void> {
    return new Promise((resolve, reject) => {
      const img = new Image();
      img.onload = () => resolve();
      img.onerror = (error) => reject(error);
      img.src = imageDataUrl;
    });
  }

  async scanQrCode(imageDataUrl: string): Promise<void> {
    return new Promise((resolve, reject) => {
      const img = new Image();
      img.onload = () => {
        const canvas = document.createElement('canvas');
        const context = canvas.getContext('2d');
        if (!context) {
          reject(new Error('Canvas context is not supported'));
          return;
        }

        canvas.width = img.width;
        canvas.height = img.height;
        context.drawImage(img, 0, 0, canvas.width, canvas.height);

        const imageData = context.getImageData(
          0,
          0,
          canvas.width,
          canvas.height,
        );
        const code = jsQR(imageData.data, imageData.width, imageData.height);

        if (code) {
          console.log('QR code result:', code.data);
          this.qrResult = code.data;
          resolve();
        } else {
          console.error('No QR code found.');
          this.qrResult = 'No QR code found';
          reject(new Error('No QR code found'));
        }
      };

      img.onerror = (error) => {
        console.error('Error loading image for scanning', error);
        this.qrResult = 'Error loading image for scanning';
        reject(error);
      };

      img.src = imageDataUrl;
    });
  }
}
