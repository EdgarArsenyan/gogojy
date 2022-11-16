package com.example.data.data.apiRepo

import com.example.data.data.util.AppConstants
import domain.model.ProductModel
import domain.model.ProductModelData
import domain.model.basket.BasketModel
import domain.model.basket.BasketModelData
import domain.model.main.MainModel
import domain.model.main.MainModelData
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET(AppConstants.MAIN_MODEL_URL)
    suspend fun getSales(
    ): Response<MainModelData>

    @GET(AppConstants.PRODUCT_URL)
    suspend fun getProduct(
    ): Response<ProductModelData>

    @GET(AppConstants.MY_CARD_PRODUCTS_URL)
    suspend fun getMyCardProducts(
    ): Response<BasketModelData>

}
