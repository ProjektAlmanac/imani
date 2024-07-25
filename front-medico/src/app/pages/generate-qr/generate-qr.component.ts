import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { QRCodeModule } from 'angularx-qrcode';
import { SessionService } from '../../services/session.service';

@Component({
  selector: 'app-generate-qr',
  standalone: true,
  imports: [CommonModule, FormsModule, QRCodeModule],
  templateUrl: './generate-qr.component.html',
  styleUrls: ['./generate-qr.component.scss'],
})
export class GenerateQrComponent {
  qrData: string = '';

  constructor(private session: SessionService) {
    const idUser: number = this.session.getIdSession();
    const isDoctor: boolean = this.session.isDoctor();
    this.qrData = `idUser:${idUser},isDoctor:${isDoctor}`;
  }

  onInputChange(newData: string) {}
}
