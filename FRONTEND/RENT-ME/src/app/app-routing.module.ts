import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserChatComponent } from './_chat/user-chat.component';
import { CartComponent } from './_cart/cart/cart.component';
import { WishlistComponent } from './_cart/wishlist/wishlist.component';
import { NotificationComponent } from './_notification/notification.component';

const routes: Routes = [
  { path: 'chat', component: UserChatComponent },
  { path: 'cart', component: CartComponent },
  { path: 'wishlist', component: WishlistComponent },
  { path: 'notification', component: NotificationComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
