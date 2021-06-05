import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { ProductService } from "../services/product.service";
import { GetAllProductsActionError, GetAllProductsActionSuccess, GetSelectedProductsActionError, GetSelectedProductsActionSuccess, ProductsActions, ProductsActionsTypes, SearchProductsActionSuccess, SelectProductActionError, SelectProductActionSuccess } from "./products.actions";
import { map, mergeMap, catchError } from 'rxjs/operators';
import { Action } from "@ngrx/store";
import { Product } from "../model/product.model";
import { of } from "rxjs";

@Injectable()
export class ProductsEffects {


  constructor(private productsService: ProductService, private effectActions: Actions) {

  }

  getAllProductsEffect = createEffect(() =>
  this.effectActions.pipe(
    ofType(ProductsActionsTypes.GET_ALL_PRODUCTS),
    mergeMap(
      (action: Action) => this.productsService.getProducts().pipe(
      map((products: Product[]) => new GetAllProductsActionSuccess(products)),
      catchError(err => of(new GetAllProductsActionError(err.message)))
    )
    )
  )
  );

  getSelectedProductsEffect = createEffect(() =>
  this.effectActions.pipe(
    ofType(ProductsActionsTypes.GET_SELECTED_PRODUCTS),
    mergeMap(
      (action: Action) => this.productsService.getSelectedProducts().pipe(
      map((products: Product[]) => new GetSelectedProductsActionSuccess(products)),
      catchError(err => of(new GetSelectedProductsActionError(err.message)))
    )
    )
  )
  );

  searchProductsEffect = createEffect(() =>
  this.effectActions.pipe(
    ofType(ProductsActionsTypes.SEARCH_PRODUCTS),
    mergeMap(
      (action: ProductsActions) => this.productsService.searchProducts(action.payload).pipe(
      map((products: Product[]) => new SearchProductsActionSuccess(products)),
      catchError(err => of(new GetSelectedProductsActionError(err.message)))
    )
    )
  )
  );

  selectProductEffect = createEffect(() =>
  this.effectActions.pipe(
    ofType(ProductsActionsTypes.SELECT_PRODUCT),
    mergeMap(
      (action: ProductsActions) => this.productsService.setSelected(action.payload).pipe(
      map((product: Product) => new SelectProductActionSuccess(product)),
      catchError(err => of(new SelectProductActionError(err.message)))
    )
    )
  )
  );


}
