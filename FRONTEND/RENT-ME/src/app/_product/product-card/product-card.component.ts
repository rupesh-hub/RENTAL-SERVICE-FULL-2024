import { Component, Input, inject } from '@angular/core';
import { Product } from '../_model/product';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.scss',
})
export class ProductCardComponent {
  @Input() product!: Product;
  @Input() first:any;
  @Input() isDetail!:boolean;

  private _router:Router = inject(Router);

  public viewDetails(productId:number):void {
    this._router.navigate(['/products/details', productId]);
  }
}
