import { Component } from '@angular/core';
import { Product } from '../../_product/_model/product';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss',
})
export class CartComponent {


  paymentMethods: any[] = [
    {
      name: 'Cash on Delivery',
      expectedDelivery: 'Friday, 29',
      charge: 7
    },
    {
      name: 'Credit Card',
      expectedDelivery: 'Thursday, 28',
      charge: 5
    },
    {
      name: 'PayPal',
      expectedDelivery: 'Wednesday, 27',
      charge: 6
    }
  ];

  cartItems: any[] = [
    {
      id: 1,
      name: 'Luxury Sedan',
      image:
        'https://w7.pngwing.com/pngs/38/708/png-transparent-car-mercedes-car-love-compact-car-vehicle-thumbnail.png',
      price: 1200,
      quantity: 2,
    },
    {
      id: 1,
      name: 'Electric SUV',
      image:
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAo7i4V_kcDsstDp4wwb5JSRSmq9bOStwjbw&s',
      price: 1200,
      quantity: 1,
    },
  ];

  removeItem(item: any): void {
    this.cartItems = this.cartItems.filter((cartItem) => cartItem !== item);
  }

  getTotal(): number {
    return this.cartItems.reduce(
      (total, item) => total + item.price * item.quantity,
      0
    );
  }

  public updateCart(): void {}
}
