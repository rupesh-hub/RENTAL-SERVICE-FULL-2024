import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductDetailsComponent } from './_products/product-details/product-details.component';
import { ProductListsComponent } from './_products/product-lists/product-lists.component';
import { ChatComponent } from './_chat/chat/chat.component';
import { CartComponent } from './_cart/cart/cart.component';
import { HomeComponent } from './_shared/home/home.component';
import { LoginComponent } from './_user/login/login.component';
import { RegisterComponent } from './_user/register/register.component';
import { ProfileComponent } from './_user/profile/profile.component';
import { ForgetPasswordComponent } from './_user/forget-password/forget-password.component';
import { ChangePasswordComponent } from './_user/change-password/change-password.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  { path: 'products/all', component: ProductListsComponent },
  {
    path: 'products/details/:id',
    component: ProductDetailsComponent,
  },
  {
    path: 'message',
    component: ChatComponent,
  },
  {
    path: 'cart',
    component: CartComponent,
  },
  {
    path: 'users/login',
    component: LoginComponent,
  },
  {
    path: 'users/register',
    component: RegisterComponent,
  },
  {
    path: 'users/profile',
    component: ProfileComponent,
  },
  {
    path: 'users/forget-password',
    component: ForgetPasswordComponent,
  },
  {
    path: 'users/change-password/:username',
    component: ChangePasswordComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
