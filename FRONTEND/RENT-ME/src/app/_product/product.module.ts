import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListProductComponent } from './list-product/list-product.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { ProductCardComponent } from './product-card/product-card.component';
import { RouterModule, Routes } from '@angular/router';
import { DataViewModule } from 'primeng/dataview';
import { Tag, TagModule } from 'primeng/tag';
import { ButtonModule } from 'primeng/button';
import { PaginatorModule } from 'primeng/paginator';
import { CommentCardComponent } from './comment-card/comment-card.component';

const routes: Routes = [
  { path: 'products/all', component: ListProductComponent },
  { path: 'products/details/:id', component: ProductDetailComponent },
];

@NgModule({
  declarations: [
    ListProductComponent,
    ProductDetailComponent,
    ProductCardComponent,
    CommentCardComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    DataViewModule,
    TagModule,
    ButtonModule,
    PaginatorModule
  ],
  exports:[
    DataViewModule,
    TagModule,
    ButtonModule,
    PaginatorModule
  ]
})
export class ProductModule {}
