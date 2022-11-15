package com.example.data.data.apiRepo

import com.example.data.data.util.URLConstants
import domain.model.ProductModel
import domain.model.ProductModelData
import domain.model.basket.BasketModel
import domain.model.basket.BasketModelData
import domain.model.main.MainModel
import domain.model.main.MainModelData
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET(URLConstants.MAIN_MODEL_URL)
    suspend fun getSales(
    ): Response<MainModel>

    @GET(URLConstants.PRODUCT_URL)
    suspend fun getProduct(
    ): Response<ProductModel>

    @GET(URLConstants.MY_CARD_PRODUCTS_URL)
    suspend fun getMyCardProducts(
    ): Response<BasketModel>

}
