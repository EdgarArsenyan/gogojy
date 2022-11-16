package com.example.data.data.apiRepo

import com.example.data.data.util.toBasketModel
import com.example.data.data.util.toBestSeller
import com.example.data.data.util.toHomeStore
import com.example.data.data.util.toProductModel
import domain.model.ProductModel
import domain.model.basket.BasketModel
import domain.model.main.BestSeller
import domain.model.main.HomeStore
import domain.repo.ApiRepository

class ApiRepoImpl(private val api: Api) : ApiRepository {

    override suspend fun getHotSales(): MutableList<HomeStore> =
        api.getSales().body()?.home_store?.map { it.toHomeStore() } as MutableList

    override suspend fun getBestSeller(): MutableList<BestSeller> =
        api.getSales().body()?.best_seller?.map { it.toBestSeller() } as MutableList

    override suspend fun getProduct(): ProductModel? = api.getProduct().body()?.toProductModel()

    override suspend fun getMyCardProd(): BasketModel? =
        api.getMyCardProducts().body()?.toBasketModel()
}