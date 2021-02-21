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
