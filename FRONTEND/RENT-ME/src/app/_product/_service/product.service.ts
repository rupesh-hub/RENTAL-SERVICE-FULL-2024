import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Product } from '../_model/product';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private imageURL: string = 'src/assets/image2.png';
  constructor() {}

  public products(): Observable<Product[] | HttpErrorResponse> {
    const products: Product[] = [
      {
        id: 1,
        name: 'Luxury Sedan',
        description:
          'The Mercedes-Benz S-Class is the epitome of luxury and sophistication, featuring cutting-edge technology and an exceptionally smooth ride.',
        rating: 4.5,
        comment: [],
        image:
          'https://w7.pngwing.com/pngs/38/708/png-transparent-car-mercedes-car-love-compact-car-vehicle-thumbnail.png',
        price: 1200,
        inventoryStatus: 'IN STOCK',
        category: 'Car',
        brand: 'Mercedes-Benz',
        model: 'S-Class',
      },
      {
        id: 2,
        name: 'Electric SUV',
        description:
          'AThe Tesla Model X is a groundbreaking electric SUV that combines incredible performance with advanced autonomous driving capabilities and a spacious interior.',
        rating: 4.8,
        comment: [],
        image:
          'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAo7i4V_kcDsstDp4wwb5JSRSmq9bOStwjbw&s',
        price: 800,
        inventoryStatus: 'OUTOFSTOCK',
        category: 'Hundai',
        brand: 'Tesla',
        model: 'Model X',
      },
      {
        id: 3,
        name: 'Sports Coupe',
        description:
          'The Porsche 911 Carrera is an iconic sports car known for its timeless design, exhilarating driving dynamics, and powerful engine options.',
        rating: 4.2,
        comment: [],
        image:
          'https://images.rawpixel.com/image_png_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIyLTA0L2pvYjY4NS0xNTQucG5n.png',
        price: 150,
        inventoryStatus: 'IN STOCK',
        category: 'Hundai',
        brand: 'Porsche',
        model: '911 Carrera',
      },
      {
        id: 4,
        name: 'Hybrid Hatchback',
        description:
          'The Toyota Prius is a pioneering hybrid vehicle that offers exceptional fuel efficiency, a comfortable interior, and a reputation for reliability.',
        rating: 4.7,
        comment: [],
        image:
          'https://t3.ftcdn.net/jpg/05/40/34/28/360_F_540342803_QwNUPFqwpa2eL27iW5E6WSuBf2OWAIeJ.jpg',
        price: 250,
        inventoryStatus: 'IN STOCK',
        category: 'Hundai',
        brand: 'Toyota',
        model: 'Prius',
      },
      {
        id: 5,
        name: 'Adventure Motorcycle',
        description:
          'The BMW R 1250 GS is an adventure motorcycle designed for both on-road and off-road exploration, featuring advanced technology and robust performance.',
        rating: 4.3,
        comment: [],
        image:
          'https://i0.wp.com/motoworldnepal.com/wp-content/uploads/2022/06/new-bmw-g-310-r-blue-metallic-front-right-76e3-2.jpg',
        price: 400,
        inventoryStatus: 'IN STOCK',
        category: 'Hundai',
        brand: 'BMW Motorrad',
        model: 'R 1250 GS',
      },
    ];

    return of(products);
  }

  public getProduct(
    productId: string
  ): Observable<Product | HttpErrorResponse> {
    return of({
      id: 5,
      name: 'Adventure Motorcycle',
      description:
        'The BMW R 1250 GS is an adventure motorcycle designed for both on-road and off-road exploration, featuring advanced technology and robust performance.',
      rating: 4.3,
      comment: [],
      image:
        'https://i0.wp.com/motoworldnepal.com/wp-content/uploads/2022/06/new-bmw-g-310-r-blue-metallic-front-right-76e3-2.jpg',
      price: 400,
      inventoryStatus: 'IN STOCK',
      category: 'Hundai',
      brand: 'BMW Motorrad',
      model: 'R 1250 GS',
    });
  }
}
