import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProductsState, ProductsStateEnum } from 'src/app/ngrx/products.reducer';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  productsState$?: Observable<ProductsState>;
  readonly productStateEnum = ProductsStateEnum;

  constructor(private store: Store<any>) { }

  ngOnInit(): void {
    this.productsState$ = this.store.pipe(
      map(state => state.catalogState)
    );
  }

}
