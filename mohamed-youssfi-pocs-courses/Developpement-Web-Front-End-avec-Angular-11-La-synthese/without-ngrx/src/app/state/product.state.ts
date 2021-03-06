
export enum ProductActionsTypes {
  GET_ALL_PRODUCTS = "[PRODUCTS] Get all products",
  GET_SELECTED_PRODUCTS = "[PRODUCTS] Get selected products",
  GET_AVAILABLE_PRODUCTS = "[PRODUCTS] Get available products",
  SEARCH_PRODUCTS = "[PRODUCTS] Search products",
  NEW_PRODUCT = "[PRODUCTS] New products",
  EDIT_PRODUCT ="[PRODUCTS] Edit products" ,
  SELECT_PRODUCT = "[PRODUCTS] Select products",
  DELETE_PRODUCT = "[PRODUCTS] Delete products"
}

export interface ActionEvent {
  type: ProductActionsTypes,
  payload?: any
}

export enum DataStateEnum {
  LOADING,
  LOADED,
  ERROR
}

export interface AppDataState<T> {
  appDataState?: DataStateEnum,
  data?: T,
  errorMessage?: string
}
