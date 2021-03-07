import { Action } from "@ngrx/store";
import { Product } from "../model/product.model";

export enum ProductsActionsTypes {
  // get all products
  GET_ALL_PRODUCTS = "[Product] get all products",
  GET_ALL_PRODUCTS_SUCCESS = "[Product] get all products success",
  GET_ALL_PRODUCTS_ERROR = "[Product] get all products error",

  // get selected products
  GET_SELECTED_PRODUCTS = "[Product] get selected products",
  GET_SELECTED_PRODUCTS_SUCCESS = "[Product] get selected products success",
  GET_SELECTED_PRODUCTS_ERROR = "[Product] get selected products error"
}

// get products actions
export class GetAllProductsAction implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.GET_ALL_PRODUCTS;

  constructor(public payload: any) { } // it is also possible to set constructor without parameters here

}

export class GetAllProductsActionSuccess implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.GET_ALL_PRODUCTS_SUCCESS;

  constructor(public payload: Product[]) { }
}

export class GetAllProductsActionError implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.GET_ALL_PRODUCTS_ERROR;

  constructor(public payload: string) { }
}

// get selected products actions
export class GetSelectedProductsAction implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.GET_SELECTED_PRODUCTS;

  constructor(public payload: any) { } // it is also possible to set constructor without parameters here
}

export class GetSelectedProductsActionSuccess implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.GET_SELECTED_PRODUCTS_SUCCESS;

  constructor(public payload: Product[]) { }
}

export class GetSelectedProductsActionError implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.GET_SELECTED_PRODUCTS_ERROR;

  constructor(public payload: string) { }
}

export type ProductsActions =
 GetAllProductsAction | GetAllProductsActionSuccess | GetAllProductsActionError |
 GetSelectedProductsAction | GetSelectedProductsActionSuccess | GetSelectedProductsActionError

