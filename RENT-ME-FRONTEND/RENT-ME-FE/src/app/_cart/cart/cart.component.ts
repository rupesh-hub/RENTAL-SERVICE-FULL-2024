import { Component } from '@angular/core';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent {

  cartItems = [
    { id: 1, name: 'Luxury Sedan', price: 1200, quantity: 2, image: 'https://media.istockphoto.com/id/1185460602/photo/3d-illustration-of-generic-red-sedan-car-front-side-view.jpg?s=612x612&w=0&k=20&c=bi8lRPp8xK_oFTDhkWQLk0I0LdiCgzcffXJnwAbSmR0=' },
    { id: 2, name: 'Electric SUV', price: 1200, quantity: 1, image: 'https://media.istockphoto.com/id/1185460602/photo/3d-illustration-of-generic-red-sedan-car-front-side-view.jpg?s=612x612&w=0&k=20&c=bi8lRPp8xK_oFTDhkWQLk0I0LdiCgzcffXJnwAbSmR0=' },
  ];

  paymentMethods = [
    { id: 1, name: 'Cash on Delivery', date: 'Friday, 29', fee: 7.00 },
    { id: 2, name: 'Credit Card', date: 'Thursday, 28', fee: 5.00 },
    { id: 3, name: 'PayPal', date: 'Wednesday, 27', fee: 6.00 },
  ];

  selectedPayment: number | null = null;

  get total(): number {
    return this.cartItems.reduce((sum, item) => sum + item.price * item.quantity, 0);
  }

  removeItem(item: any): void {
    this.cartItems = this.cartItems.filter(i => i.id !== item.id);
  }

  selectPayment(id: number): void {
    this.selectedPayment = id;
  }

  creditCards: any[] = [
    { id: 1, type: 'visa', lastFourDigits: '7830', expirationDate: '06/24', isDefault: true, isExpired: false },
    { id: 2, type: 'visa', lastFourDigits: '5775', expirationDate: '06/24', isDefault: false, isExpired: false },
    { id: 3, type: 'mastercard', lastFourDigits: '1075', expirationDate: '02/25', isDefault: false, isExpired: false },
    { id: 4, type: 'mastercard', lastFourDigits: '4962', expirationDate: '06/24', isDefault: false, isExpired: true },
  ];
}
