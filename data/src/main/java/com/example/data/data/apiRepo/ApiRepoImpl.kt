package com.example.data.data.apiRepo

import domain.model.ProductModel
import domain.model.ProductModelData
import domain.model.basket.BasketModel
import domain.model.basket.BasketModelData
import domain.model.main.BestSeller
import domain.model.main.BestSellerData
import domain.model.main.HomeStore
import domain.model.main.HomeStoreData
import domain.repo.ApiRepository

class ApiRepoImpl(private val api: Api) : ApiRepository{

    override suspend fun getHotSales(): MutableList<HomeStore> =
        api.getSales().body()?.home_store as MutableList

    override suspend fun getBestSeller(): MutableList<BestSeller> =
        api.getSales().body()?.best_seller as MutableList

    override suspend fun getProduct(): ProductModel = api.getProduct().body() as ProductModel

    override suspend fun getMyCardProd(): BasketModel = api.getMyCardProducts().body() as BasketModel
}