import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { UserServiceService } from '../../_user/user-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit, OnDestroy {
  isLoggedIn: boolean = true;
  //loginSubscription: Subscription | undefined; // Track subscription for cleanup

  constructor(
    private router: Router,
    private _userService: UserServiceService
  ) {}

  // Unsubscribe from the loginStatus$ observable to prevent memory leaks
  ngOnDestroy(): void {
    // if (this.loginSubscription) {
    //   this.loginSubscription.unsubscribe();
    // }
  }

  // Subscribe to login status changes from UserService
  public ngOnInit(): void {
    // this.loginSubscription = this._userService.loginStatus$.subscribe(
    //   (status) => {
    //     this.isLoggedIn = status;
    //     console.log('Login status:', status);
    //   }
    // );
  }

  // Navigate to login page
  public login(): void {
    this.router.navigate(['/users/login']);
  }

  // Log out and update the UserService's login status
  public logout(): void {
    this._userService.logout(); // This should handle setting loginStatus to false
  }

  // Dropdown toggle function
  isOpen = false;

  toggleDropdown() {
    this.isOpen = !this.isOpen;
  }

  isNotificationOpen = false;
  unreadCount = 2;
  notifications: any[] = [
    {
      id: 1,
      message: 'Your order has been shipped',
      time: '5 minutes ago',
      isRead: false,
    },
    {
      id: 2,
      message: 'New product available',
      time: '1 hour ago',
      isRead: true,
    },
  ];

  toggleNotifications() {
    this.isNotificationOpen = !this.isNotificationOpen;
  }
}
