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

  // New product
  NEW_PRODUCT = "[Product] New product",
  NEW_PRODUCT_SUCCESS = "[Product] new product success",
  NEW_PRODUCT_ERROR = "[Product] new product error",

  // Save product
  SAVE_PRODUCT = "[Product] Save product",
  SAVE_PRODUCT_SUCCESS = "[Product] Save product success",
  SAVE_PRODUCT_ERROR = "[Product] Save product error",

  // Edit product
  EDIT_PRODUCT = "[Product] Edit product",
  EDIT_PRODUCT_SUCCESS = "[Product] Edit product success",
  EDIT_PRODUCT_ERROR = "[Product] Edit product error",

  // Update product
  UPDATE_PRODUCT = "[Product] Update product",
  UPDATE_PRODUCT_SUCCESS = "[Product] Update product success",
  UPDATE_PRODUCT_ERROR = "[Product] Update product error",
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

//  new product actions
export class NewProductAction implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.NEW_PRODUCT;

  constructor(public payload: any) { }
}

export class NewProductActionSuccess implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.NEW_PRODUCT_SUCCESS;

  constructor(public payload: any) { }
}

export class NewProductActionError implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.NEW_PRODUCT_ERROR;

  constructor(public payload: string) { }
}

//  Save product actions
export class SaveProductAction implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.SAVE_PRODUCT;

  constructor(public payload: Product) { }
}

export class SaveProductActionSuccess implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.SAVE_PRODUCT_SUCCESS;

  constructor(public payload: Product) { }
}

export class SaveProductActionError implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.SAVE_PRODUCT_ERROR;

  constructor(public payload: string) { }
}

//  Edit product actions
export class EditProductAction implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.EDIT_PRODUCT;

  constructor(public payload: Number) { }
}

export class EditProductActionSuccess implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.EDIT_PRODUCT_SUCCESS;

  constructor(public payload: Product) { }
}

export class EditProductActionError implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.EDIT_PRODUCT_ERROR;

  constructor(public payload: string) { }
}

//  Update product actions
export class UpdateProductAction implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.UPDATE_PRODUCT;

  constructor(public payload: Number) { }
}

export class UpdateProductActionSuccess implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.UPDATE_PRODUCT_SUCCESS;

  constructor(public payload: Product) { }
}

export class UpdateProductActionError implements Action {
  type: ProductsActionsTypes = ProductsActionsTypes.UPDATE_PRODUCT_ERROR;

  constructor(public payload: string) { }
}

export type ProductsActions =
 GetAllProductsAction | GetAllProductsActionSuccess | GetAllProductsActionError |
 GetSelectedProductsAction | GetSelectedProductsActionSuccess | GetSelectedProductsActionError |
 SearchProductsAction | SearchProductsActionSuccess | SearchProductsActionError |
 SelectProductAction | SelectProductActionSuccess | SelectProductActionError |
 DeleteProductAction | DeleteProductActionSuccess | DeleteProductActionError |
 NewProductAction | NewProductActionSuccess | NewProductActionError |
 SaveProductAction | SaveProductActionSuccess | SaveProductActionError |
 EditProductAction | EditProductActionSuccess | EditProductActionError |
 UpdateProductAction | UpdateProductActionSuccess | UpdateProductActionError

