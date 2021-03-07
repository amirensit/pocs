import { Action } from "@ngrx/store";
import { Product } from "../model/product.model";
import { GetAllProductsAction, ProductsActions, ProductsActionsTypes } from "./products.actions";

export enum ProductsStateEnum {
  LOADING = "Loading",
  LOADED = "Loaded",
  ERROR = "Error",
  INITIAL = "Initial"
}

export interface ProductsState {
  products: Product[],
  errorMsg: string,
  dataState: ProductsStateEnum
}

const initState: ProductsState = {
  products: [],
  errorMsg: '',
  dataState: ProductsStateEnum.INITIAL
}

export function productsReducer(state: ProductsState = initState, action: Action): ProductsState {
  switch(action.type) {
    case ProductsActionsTypes.GET_ALL_PRODUCTS:
      return {
        ...state,
        dataState: ProductsStateEnum.LOADING
      }
    case ProductsActionsTypes.GET_ALL_PRODUCTS_SUCCESS:
      return {
        ...state,
        dataState: ProductsStateEnum.LOADED,
        products: (<ProductsActions>action).payload
      }
    case ProductsActionsTypes.GET_ALL_PRODUCTS_ERROR:
      return {
        ...state,
        dataState: ProductsStateEnum.ERROR,
        errorMsg: (action as ProductsActions).payload
      }
    case ProductsActionsTypes.GET_SELECTED_PRODUCTS:
      return {
        ...state,
        dataState: ProductsStateEnum.LOADING
      }
    case ProductsActionsTypes.GET_SELECTED_PRODUCTS_SUCCESS:
      return {
        ...state,
        dataState: ProductsStateEnum.LOADED,
        products: (<ProductsActions>action).payload
      }
    case ProductsActionsTypes.GET_SELECTED_PRODUCTS_ERROR:
      return {
        ...state,
        dataState: ProductsStateEnum.ERROR,
        errorMsg: (action as ProductsActions).payload
      }
    default:
      return {...state}
  }
}
