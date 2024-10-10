import {Component, inject, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {FaIconComponent, FaIconLibrary} from "@fortawesome/angular-fontawesome";
import {fontAwesomeIcons} from "./shared/font-awesome-icons";
import {Auth2AuthService} from "./auth/auth2-auth.service";
import {NavbarComponent} from "./layout/navbar/navbar.component";
import {ToastService} from "./shared/toast.service";
import {NgbToast} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'wac-root',
  standalone: true,
  imports: [RouterOutlet, FaIconComponent, NavbarComponent, NgbToast],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit{
  title = 'CHAT APPLICATION';

  private faIconLibrary = inject(FaIconLibrary);
  private oauth2Service = inject(Auth2AuthService);
  protected toastService = inject(ToastService);

  public ngOnInit():void {
    this.initFontAwesome();
    this.initAuthentication();
  }

  private initAuthentication(){
     this.oauth2Service.initAuthentication();
  }

  private initFontAwesome():void{
    this.faIconLibrary.addIcons(...fontAwesomeIcons);
  }

}
