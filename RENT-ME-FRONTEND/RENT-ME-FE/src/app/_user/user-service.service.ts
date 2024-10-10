import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor() { }

  private loginStatus = new BehaviorSubject<boolean>(false);
  loginStatus$ = this.loginStatus.asObservable();

  public login(status:boolean):void {
    this.loginStatus.next(status);
  }

  public logout():void {
    this.loginStatus.next(false);
  }

  public getLoginStatus(): boolean {
    return this.loginStatus.getValue();
  }

}
