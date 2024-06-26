export interface Product {
  id: number;
  name: string;
  description: string;
  rating: number;
  comment: Comment[];
  image: string[];
  price: number;
}