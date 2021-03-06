import { Action } from "@ngrx/store";

export enum ProductsActionsTypes {
  GET_ALL_PRODUCTS = "[Product] get all products",
  GET_ALL_PRODUCTS_SUCCESS = "[Product] get all products success",
  GET_ALL_PRODUCTS_ERROR = "[Product] get all products error"
}

export class GetAllProductsAction implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.GET_ALL_PRODUCTS;

  constructor(public payload: any) { } // it is also possible to set constructor without parameters here

}

export class GetAllProductsActionSuccess implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.GET_ALL_PRODUCTS_SUCCESS;

  constructor(public payload: any) { }

}

export class GetAllProductsActionError implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.GET_ALL_PRODUCTS_ERROR;

  constructor(public payload: string) { }

}

