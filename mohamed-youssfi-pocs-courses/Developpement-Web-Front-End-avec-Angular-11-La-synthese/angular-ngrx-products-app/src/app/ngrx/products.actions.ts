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
  GET_SELECTED_PRODUCTS_ERROR = "[Product] get selected products error",

  // Search products
  SEARCH_PRODUCTS = "[Product] Search products",
  SEARCH_PRODUCTS_SUCCESS = "[Product] Search products success",
  SEARCH_PRODUCTS_ERROR = "[Product] Search products error",

  // select product
  SELECT_PRODUCT = "[Product] select product",
  SELECT_PRODUCT_SUCCESS = "[Product] select product success",
  SELECT_PRODUCT_ERROR = "[Product] select product error",

  // delete product
  DELETE_PRODUCT = "[Product] delete product",
  DELETE_PRODUCT_SUCCESS = "[Product] delete product success",
  DELETE_PRODUCT_ERROR = "[Product] delete product error",
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

// search products actions
export class SearchProductsAction implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.SEARCH_PRODUCTS;

  constructor(public payload: string) { }
}

export class SearchProductsActionSuccess implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.SEARCH_PRODUCTS_SUCCESS;

  constructor(public payload: Product[]) { }
}

export class SearchProductsActionError implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.SEARCH_PRODUCTS_ERROR;

  constructor(public payload: string) { }
}

//  select product actions
export class SelectProductAction implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.SELECT_PRODUCT;

  constructor(public payload: Product) { }
}

export class SelectProductActionSuccess implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.SELECT_PRODUCT_SUCCESS;

  constructor(public payload: Product) { }
}

export class SelectProductActionError implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.SELECT_PRODUCT_ERROR;

  constructor(public payload: string) { }
}

//  delete product actions
export class DeleteProductAction implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.DELETE_PRODUCT;

  constructor(public payload: Product) { }
}

export class DeleteProductActionSuccess implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.DELETE_PRODUCT_SUCCESS;

  constructor(public payload: Product) { }
}

export class DeleteProductActionError implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.DELETE_PRODUCT_ERROR;

  constructor(public payload: string) { }
}

export type ProductsActions =
 GetAllProductsAction | GetAllProductsActionSuccess | GetAllProductsActionError |
 GetSelectedProductsAction | GetSelectedProductsActionSuccess | GetSelectedProductsActionError |
 SearchProductsAction | SearchProductsActionSuccess | SearchProductsActionError |
 SelectProductAction | SelectProductActionSuccess | SelectProductActionError |
 DeleteProductAction | DeleteProductActionSuccess | DeleteProductActionError

