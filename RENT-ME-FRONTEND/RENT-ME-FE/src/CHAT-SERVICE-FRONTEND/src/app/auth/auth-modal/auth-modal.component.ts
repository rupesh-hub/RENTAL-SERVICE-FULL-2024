import {Component, inject} from '@angular/core';
import {Auth2AuthService} from "../auth2-auth.service";
import {NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'wac-auth-modal',
  standalone: true,
  imports: [
    NgOptimizedImage
  ],
  templateUrl: './auth-modal.component.html',
  styleUrl: './auth-modal.component.scss'
})
export class AuthModalComponent {

  oauth2Service = inject(Auth2AuthService);

  login():void{
    this.oauth2Service.login();
  }

}
