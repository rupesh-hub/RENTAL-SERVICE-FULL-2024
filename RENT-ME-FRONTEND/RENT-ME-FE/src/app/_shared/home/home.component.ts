import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  products: any[] = [
    {
      id: 1,
      name: 'STORMTROOPER HELMET',
      price: 1299.99,
      image: 'https://www.apple.com/newsroom/images/product/watch/standard/Apple_watch_series_5-meridian-face-091019_carousel.jpg.large.jpg',
      sizes: ['S', 'M', 'L', 'XL'],
      durability: 75
    },
    {
      id: 2,
      name: 'DARTH VADER HELMET',
      price: 1499.99,
      image: 'https://www.apple.com/newsroom/images/product/watch/standard/Apple_watch_series_5-meridian-face-091019_carousel.jpg.large.jpg',
      sizes: ['S', 'M', 'L', 'XL'],
      durability: 85
    },
    {
      id: 3,
      name: 'MANDALORIAN HELMET',
      price: 1599.99,
      image: 'https://www.apple.com/newsroom/images/product/watch/standard/Apple_watch_series_5-meridian-face-091019_carousel.jpg.large.jpg',
      sizes: ['S', 'M', 'L', 'XL'],
      durability: 90
    },
    {
      id: 4,
      name: 'MANDALORIAN HELMET',
      price: 1599.99,
      image: 'https://www.apple.com/newsroom/images/product/watch/standard/Apple_watch_series_5-meridian-face-091019_carousel.jpg.large.jpg',
      sizes: ['S', 'M', 'L', 'XL'],
      durability: 90
    }
  ];

}
