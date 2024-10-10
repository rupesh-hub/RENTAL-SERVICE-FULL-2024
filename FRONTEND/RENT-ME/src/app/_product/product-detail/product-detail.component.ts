import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../_model/product';
import { ProductService } from '../_service/product.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrl: './product-detail.component.scss',
})
export class ProductDetailComponent {
  private _activatedRoute: ActivatedRoute = inject(ActivatedRoute);
  private _productService: ProductService = inject(ProductService);
  private productId!: string;
  public product!: Product;

  constructor() {
    this.productId = this._activatedRoute.snapshot.paramMap.get('id')!;
    this._productService
      .getProduct(this.productId)
      .subscribe((data: any) => (this.product = data)),
      (error: HttpErrorResponse) => console.log(error);
  }
}
