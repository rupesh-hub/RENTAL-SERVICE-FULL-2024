import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Product } from '../_model/product';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor() {}

  public products(): Observable<Product[] | HttpErrorResponse> {
    const products: Product[] = [
      {
        id: 1,
        name: 'Laptop',
        description: 'A high-performance laptop for all your needs.',
        rating: 4.5,
        comment:[],
        // comment: [
        //   {
        //     id: 1,
        //     productId: 1,
        //     username: 'JohnDoe',
        //     comment: 'Excellent laptop with great performance.',
        //     timestamp: new Date('2023-06-01T10:00:00Z'),
        //   },
        // ],
        image: ['laptop1.jpg', 'laptop2.jpg'],
        price: 1200,
      },
      {
        id: 2,
        name: 'Smartphone',
        description: 'A sleek smartphone with a powerful camera.',
        rating: 4.8,
        // comment: [
        //   {
        //     id: 2,
        //     productId: 2,
        //     username: 'JaneSmith',
        //     comment: 'Fantastic camera quality and smooth performance.',
        //     timestamp: new Date('2023-06-02T11:00:00Z'),
        //   },
        // ],
        comment:[],
        image: ['smartphone1.jpg', 'smartphone2.jpg'],
        price: 800,
      },
      {
        id: 3,
        name: 'Headphones',
        description: 'Noise-cancelling headphones for immersive sound.',
        rating: 4.2,
        // comment: [
        //   {
        //     id: 3,
        //     productId: 3,
        //     username: 'AliceJones',
        //     comment: 'Great sound quality and comfortable to wear.',
        //     timestamp: new Date('2023-06-03T12:00:00Z'),
        //   },
        // ],
        comment:[],
        image: ['headphones1.jpg', 'headphones2.jpg'],
        price: 150,
      },
      {
        id: 4,
        name: 'Smartwatch',
        description: 'A smartwatch with fitness tracking features.',
        rating: 4.7,
        // comment: [
        //   {
        //     id: 4,
        //     productId: 4,
        //     username: 'BobBrown',
        //     comment:
        //       'Very useful for tracking my workouts and daily activities.',
        //     timestamp: new Date('2023-06-04T13:00:00Z'),
        //   },
        // ],
        comment:[],
        image: ['smartwatch1.jpg', 'smartwatch2.jpg'],
        price: 250,
      },
      {
        id: 5,
        name: 'Tablet',
        description: 'A lightweight tablet with a large display.',
        rating: 4.3,
        // comment: [
        //   {
        //     id: 5,
        //     productId: 5,
        //     username: 'CharlieGreen',
        //     comment: 'Perfect for reading and browsing the web.',
        //     timestamp: new Date('2023-06-05T14:00:00Z'),
        //   },
        // ],
        comment:[],
        image: ['tablet1.jpg', 'tablet2.jpg'],
        price: 400,
      },
    ];

    return of(products);
  }
}
