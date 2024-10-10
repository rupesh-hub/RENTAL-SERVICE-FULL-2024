import {Component, effect, inject} from '@angular/core';
import {Auth2AuthService} from "../../auth/auth2-auth.service";
import {ConnectedUser} from "../../shared/model/user.model";
import {NgOptimizedImage} from "@angular/common";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {NgbDropdown, NgbDropdownMenu, NgbDropdownToggle, NgbOffcanvas} from "@ng-bootstrap/ng-bootstrap";
import {NewConversationComponent} from "./new-conversation/new-conversation.component";

@Component({
  selector: 'wac-navbar',
  standalone: true,
  imports: [
    NgOptimizedImage,
    FaIconComponent,
    NgbDropdown,
    NgbDropdownToggle,
    NgbDropdownMenu
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

  protected oauth2Service: Auth2AuthService = inject(Auth2AuthService);
  protected connectedUser:ConnectedUser | undefined;
  protected offCanvasService = inject(NgbOffcanvas);

  constructor() {
    this.listenToFetchUser();
  }

  private listenToFetchUser() {
    effect(() => {
      const userState = this.oauth2Service.fetchUser();
      if (
        userState.status === 'OK'
        && userState.value?.email
        && userState.value.email !== this.oauth2Service.notConnected
      ) {
        this.connectedUser = userState.value;
      }
    });
  }

  logout():void {
    this.oauth2Service.logout();
    this.connectedUser = undefined;
  }

  editProfile():void{
    this.oauth2Service.goToProfilePage();
  }

  openNewConversation():void{
    this.offCanvasService.open(NewConversationComponent,
      {position: "start", container:"#main", panelClass:"offcanvas"} );
  }

}
