import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ButtonModule } from 'primeng/button';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { InputTextModule } from 'primeng/inputtext';
import { DataViewModule } from 'primeng/dataview';
import { DropdownModule } from 'primeng/dropdown';
import { TagModule } from 'primeng/tag';
import { MenubarModule } from 'primeng/menubar';
import { AvatarModule } from 'primeng/avatar';
import { AvatarGroupModule } from 'primeng/avatargroup';
import { MenuModule } from 'primeng/menu';
import { BadgeModule } from 'primeng/badge';
import { NavbarComponent } from './_navbar/navbar.component';
import { ProductModule } from './_product/product.module';
import { NotificationComponent } from './_notification/notification.component';
import { CartComponent } from './_cart/cart/cart.component';
import { WishlistComponent } from './_cart/wishlist/wishlist.component';
import { UserChatComponent } from './_chat/user-chat.component';
import { TreeDragDropService } from 'primeng/api';
import { InputNumberModule } from 'primeng/inputnumber';
import { TableModule } from 'primeng/table';
import { CardModule } from 'primeng/card';
import { UserModule } from './_user/user.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    NotificationComponent,
    CartComponent,
    WishlistComponent,
    UserChatComponent
  ],
  imports: [
    UserModule,
    BrowserModule,
    AppRoutingModule,
    ButtonModule,
    IconFieldModule,
    InputIconModule,
    InputTextModule,
    DataViewModule,
    DropdownModule,
    TagModule,
    MenubarModule,
    AvatarModule,
    AvatarGroupModule,
    MenuModule,
    BadgeModule,
    ProductModule,
    // OrderListModule
    InputNumberModule,
    TableModule,
    CardModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
