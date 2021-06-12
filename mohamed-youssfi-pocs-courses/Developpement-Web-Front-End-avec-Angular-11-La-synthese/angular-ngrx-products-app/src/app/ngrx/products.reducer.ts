import { Action } from "@ngrx/store";
import { Product } from "../model/product.model";
import { GetAllProductsAction, ProductsActions, ProductsActionsTypes } from "./products.actions";

export enum ProductsStateEnum {
  LOADING = "Loading",
  LOADED = "Loaded",
  ERROR = "Error",
  INITIAL = "Initial",
  NEW = "New",
  EDIT = "Edit"
}

export interface ProductsState {
  products: Product[],
  errorMsg: string,
  dataState: ProductsStateEnum,
  currentProduct: Product | null // we need this attribute in case of edit
}

const initState: ProductsState = {
  products: [],
  errorMsg: '',
  dataState: ProductsStateEnum.INITIAL,
  currentProduct: null
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
    case ProductsActionsTypes.SEARCH_PRODUCTS:
      return {
        ...state,
        dataState: ProductsStateEnum.LOADING
      }
    case ProductsActionsTypes.SEARCH_PRODUCTS_SUCCESS:
      return {
        ...state,
        dataState: ProductsStateEnum.LOADED,
        products: (<ProductsActions>action).payload
      }
    case ProductsActionsTypes.SEARCH_PRODUCTS_ERROR:
      return {
        ...state,
        dataState: ProductsStateEnum.ERROR,
        errorMsg: (action as ProductsActions).payload
      }
    case ProductsActionsTypes.SELECT_PRODUCT:
      return {
        ...state,
        dataState: ProductsStateEnum.LOADING
      }
    case ProductsActionsTypes.SELECT_PRODUCT_SUCCESS: { // case clauses inside switch blocks don't create their own block scopes
      let product: Product = (action as ProductsActions).payload;
      let listProducts = [...state.products];
      let data =  listProducts.map(p =>
          p.id == product.id ? product : p
        );
      return {
        ...state,
        products: data,
        dataState: ProductsStateEnum.LOADED,
      }
    }
    case ProductsActionsTypes.SELECT_PRODUCT_ERROR:
      return {
        ...state,
        dataState: ProductsStateEnum.ERROR,
        errorMsg: (action as ProductsActions).payload
      }
    case ProductsActionsTypes.DELETE_PRODUCT:
      return {
        ...state,
        dataState: ProductsStateEnum.LOADING
      }
    case ProductsActionsTypes.DELETE_PRODUCT_SUCCESS:
      let product: Product = (action as ProductsActions).payload;
      let listProducts = [...state.products];
      let index = listProducts.indexOf(product);
      listProducts.splice(index, 1);
        return {
          ...state,
          products: listProducts,
          dataState: ProductsStateEnum.LOADED,
        }
    case ProductsActionsTypes.DELETE_PRODUCT_ERROR:
      return {
        ...state,
        dataState: ProductsStateEnum.ERROR,
        errorMsg: (action as ProductsActions).payload
      }
    case ProductsActionsTypes.NEW_PRODUCT:
    return {
      ...state,
      dataState: ProductsStateEnum.LOADING
    }
    case ProductsActionsTypes.NEW_PRODUCT_SUCCESS:
      return {
        ...state,
        dataState: ProductsStateEnum.NEW,
      }
    case ProductsActionsTypes.NEW_PRODUCT_ERROR:
      return {
        ...state,
        dataState: ProductsStateEnum.ERROR,
        errorMsg: (action as ProductsActions).payload
      }
    case ProductsActionsTypes.SAVE_PRODUCT:
      return {
        ...state,
        dataState: ProductsStateEnum.LOADING
    }
    case ProductsActionsTypes.SAVE_PRODUCT_SUCCESS:
      {
        let listProducts = [...state.products];
        listProducts.push((action as ProductsActions).payload)
      return {
        ...state,
        products: listProducts,
        dataState: ProductsStateEnum.LOADED,
      }
    }
    case ProductsActionsTypes.SAVE_PRODUCT_ERROR:
      return {
        ...state,
        dataState: ProductsStateEnum.ERROR,
        errorMsg: (action as ProductsActions).payload
      }
      case ProductsActionsTypes.EDIT_PRODUCT:
        return {
          ...state,
          dataState: ProductsStateEnum.LOADING
      }
      case ProductsActionsTypes.EDIT_PRODUCT_SUCCESS:
        {
        return {
          ...state,
          currentProduct: (action as ProductsActions).payload,
          dataState: ProductsStateEnum.EDIT,
        }
      }
      case ProductsActionsTypes.EDIT_PRODUCT_ERROR:
        return {
          ...state,
          dataState: ProductsStateEnum.ERROR,
          errorMsg: (action as ProductsActions).payload
        }
        case ProductsActionsTypes.UPDATE_PRODUCT:
        return {
          ...state,
          dataState: ProductsStateEnum.LOADING
      }
      case ProductsActionsTypes.UPDATE_PRODUCT_SUCCESS:
        {
          let product = (action as ProductsActions).payload;
          let data =  state.products.map(p =>
            p.id == product.id ? product : p
          );
        return {
          ...state,
          products: data,
          dataState: ProductsStateEnum.LOADED,
        }
      }
      case ProductsActionsTypes.UPDATE_PRODUCT_ERROR:
        return {
          ...state,
          dataState: ProductsStateEnum.ERROR,
          errorMsg: (action as ProductsActions).payload
        }
  default:
    return {...state}
  }
}
