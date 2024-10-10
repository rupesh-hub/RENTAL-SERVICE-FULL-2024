import { Component } from '@angular/core';
import { products, Product } from '../_model/product';

@Component({
  selector: 'app-product-lists',
  templateUrl: './product-lists.component.html',
  styleUrl: './product-lists.component.scss'
})
export class ProductListsComponent {

  products: Product[] = products;

}
