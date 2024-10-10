import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductListsComponent } from './product-lists/product-lists.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'products/all', component: ProductDetailsComponent },
  {
    path: 'products/:id',
    component: ProductDetailsComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
@NgModule({
  declarations: [ProductListsComponent, ProductDetailsComponent],
  imports: [CommonModule],
  exports:[ProductListsComponent, ProductDetailsComponent]
})
export class ProductModule {}
