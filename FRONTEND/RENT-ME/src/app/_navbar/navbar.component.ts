import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

  items: MenuItem[] = [];
  profileItems: MenuItem[] = [];
  isLoggedIn: boolean = false;
  notificationCount: number = 4;
  private cartItemCount:number=2;

  private _router:Router = inject(Router);

  constructor() {
    this.items = [
      {
        label: 'Home',
        icon: 'pi pi-fw pi-home',
      },
      {
        label: 'Products',
        icon: 'pi pi-fw pi-shop',
        command: () => {
          this.productPage()
        }
      },
      {
        label: 'Cart',
        icon: 'pi pi-fw pi-shopping-bag'
      }
    ];

    this.profileItems = [
      { label: 'View Profile', icon: 'pi pi-user' },
      { label: 'Logout', icon: 'pi pi-sign-out' },
    ];
  }

  onEdit() {
    console.log('Edit clicked');
  }
  onDelete() {
    console.log('Delete clicked');
  }
  onNotificationClick() {
    console.log('Notification clicked');
  }

  public login(): void {
    this.isLoggedIn = !this.isLoggedIn;
  }

  private productPage():void{
    this._router.navigate(['/products/all'])
  }
}
