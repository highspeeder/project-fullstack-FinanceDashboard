//@reduxjs/toolkit/query/react 패키지를 사용하여 API 요청을 관리하는
//Redux Toolkit Query의 createApi 함수를 호출하여 API 클라이언트를 생성한다
import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import {
  GetKpisResponse,
  GetProductsResponse,
  GetTransactionsResponse,
} from "./types";

//createApi 함수를 사용하여 api 객체를 생성하고 내보낸다.
export const api = createApi({
  //baseQuery 속성에는 fetchBaseQuery 함수를 호출하여 기본 쿼리를 설정.
  baseQuery: fetchBaseQuery({ baseUrl: import.meta.env.VITE_BASE_URL }),

  //reducerPath 속성은 Redux Toolkit에서 사용할 리듀서의 경로를 설정한다.
  //reducer는 Action이 일어나면 store의 상태를 변경한다.
  reducerPath: "main",

  //태그는 쿼리 결과를 식별하고, 해당 쿼리 결과가 변경될 때 캐시를 업데이트하는 데 도움이 된다.
  tagTypes: ["Kpis", "products", "transactions"],

  //전반적이 컨트롤러의 속성들을 지정한다.
  endpoints: (build) => ({
    //서버로부터 받아올 데이터는 Array<GetKpisResponse>
    getKpis: build.query<Array<GetKpisResponse>, void>({
      query: () => "kpi/kpis", //서버의 서버에 전송할 쿼리 URL
      providesTags: ["Kpis"], //해당 쿼리가 변경될 때 업데이트되어야 하는 태그
    }),
    getProducts: build.query<Array<GetProductsResponse>, void>({
      query: () => "product/products",
      providesTags: ["products"],
    }),
    getTransactions: build.query<Array<GetTransactionsResponse>, void>({
      query: () => "transaction/transactions",
      providesTags: ["transactions"],
    }),
  }),
});

//api 객체에서 useGetKpisQuery와 useGetProductsQuery를 내보냅니다.
export const { useGetKpisQuery, useGetProductsQuery, useGetTransactionsQuery } =
  api;
