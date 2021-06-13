import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { GetAllProductsAction, GetSelectedProductsAction, ProductsActionsTypes, SearchProductsAction } from 'src/app/ngrx/products.actions';
import { ProductsState } from 'src/app/ngrx/products.reducer';

@Component({
  selector: 'app-products-navbar',
  templateUrl: './products-navbar.component.html',
  styleUrls: ['./products-navbar.component.css']
})
export class ProductsNavbarComponent implements OnInit {

  state: ProductsState | null = null;
  readonly ProductActions = ProductsActionsTypes;

  constructor(private store: Store<any>, private router: Router) { }

  ngOnInit(): void {
    this.store.subscribe(state => this.state = state.catalogState)
  }

  onGetAllProducts() {
    this.store.dispatch(new GetAllProductsAction({}));
  }

  onGetSelectedProducts() {
    this.store.dispatch(new GetSelectedProductsAction({}));
  }

  onNewProducts() {
    this.router.navigate(['/newProduct']);
  }

  onSearch(dataForm: any) {
    this.store.dispatch(new SearchProductsAction(dataForm.keyword));
  }

}
