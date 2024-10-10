export interface Product {
    id: number;
    brand: string;
    model: string;
    type: string;
    price: number;
    rating: number;
    image: string;
    inStock: boolean;
  }
  
  export const products: Product[] = [
    {
      id: 1,
      brand: 'Mercedes-Benz',
      model: 'Luxury Sedan (S-Class)',
      type: 'Sedan',
      price: 1200.00,
      rating: 4.5,
      image: 'https://www.mercedes-benz.dk/content/dam/hq/passengercars/cars/s-class/s-class-saloon-wv223-pi/modeloverview/09-2022/images/mercedes-benz-s-class-wv223-modeloverview-696x392-09-2022.png',
      inStock: true
    },
    {
      id: 2,
      brand: 'Tesla',
      model: 'Electric SUV (Model X)',
      type: 'SUV',
      price: 800.00,
      rating: 4.8,
      image: 'https://www.mercedes-benz.dk/content/dam/hq/passengercars/cars/s-class/s-class-saloon-wv223-pi/modeloverview/09-2022/images/mercedes-benz-s-class-wv223-modeloverview-696x392-09-2022.png',
      inStock: false
    },
    {
      id: 3,
      brand: 'Porsche',
      model: 'Sports Coupe (911 Carrera)',
      type: 'Coupe',
      price: 150.00,
      rating: 4.2,
      image: 'https://www.mercedes-benz.dk/content/dam/hq/passengercars/cars/s-class/s-class-saloon-wv223-pi/modeloverview/09-2022/images/mercedes-benz-s-class-wv223-modeloverview-696x392-09-2022.png', // /assets/
      inStock: true
    }
  ];