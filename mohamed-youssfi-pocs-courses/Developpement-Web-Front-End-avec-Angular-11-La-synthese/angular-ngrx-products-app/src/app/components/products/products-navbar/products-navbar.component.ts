import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { GetAllProductsAction, GetSelectedProductsAction, SearchProductsAction } from 'src/app/ngrx/products.actions';

@Component({
  selector: 'app-products-navbar',
  templateUrl: './products-navbar.component.html',
  styleUrls: ['./products-navbar.component.css']
})
export class ProductsNavbarComponent implements OnInit {

  constructor(private store: Store<any>) { }

  ngOnInit(): void {
  }

  onGetAllProducts() {
    this.store.dispatch(new GetAllProductsAction({}));
  }

  onGetSelectedProducts() {
    this.store.dispatch(new GetSelectedProductsAction({}));
  }

  onSearch(dataForm: any) {
    this.store.dispatch(new SearchProductsAction(dataForm.keyword));
  }

}
