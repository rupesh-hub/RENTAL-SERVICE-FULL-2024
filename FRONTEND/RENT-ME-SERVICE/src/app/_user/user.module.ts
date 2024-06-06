import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthenticationComponent } from './authentication/authentication.component';
import { RegisterComponent } from './register/register.component';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'users/register', component: RegisterComponent },
  { path: 'users/authentication', component: AuthenticationComponent }
];

@NgModule({
  declarations: [
    AuthenticationComponent,
    RegisterComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class UserModule { }
