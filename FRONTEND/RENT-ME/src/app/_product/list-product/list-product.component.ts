import { Component, OnInit, inject } from '@angular/core';
import { ProductService } from '../_service/product.service';
import { Product } from '../_model/product';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrl: './list-product.component.scss',
})
export class ListProductComponent implements OnInit {
  private _productService: ProductService = inject(ProductService);
  public products: Product[] = [];
  public first:number = 1;
  public rows:number = 10;

  ngOnInit(): void {
    this._productService.products().subscribe(
      (data: Product[] | any) => (this.products = data),
      (error: HttpErrorResponse) => console.log(error)
    );
  }

  onPageChange(event:any):void {
    console.log(event)
  }
}
