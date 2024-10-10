import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './_shared/navbar/navbar.component';
import { ProductModule } from './_products/product.module';
import { ChatComponent } from './_chat/chat/chat.component';
import { CartComponent } from './_cart/cart/cart.component';
import { HomeComponent } from './_shared/home/home.component';
import { LoginComponent } from './_user/login/login.component';
import { RegisterComponent } from './_user/register/register.component';
import { ProfileComponent } from './_user/profile/profile.component';
import { ForgetPasswordComponent } from './_user/forget-password/forget-password.component';
import { ChangePasswordComponent } from './_user/change-password/change-password.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ChatComponent,
    CartComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    ForgetPasswordComponent,
    ChangePasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ProductModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
