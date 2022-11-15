package domain.repo

import domain.model.ProductModel
import domain.model.basket.Basket
import domain.model.basket.BasketModel
import domain.model.main.BestSeller
import domain.model.main.HomeStore

interface ApiRepository {

    suspend fun getHotSales(): MutableList<HomeStore>
    suspend fun getBestSeller(): MutableList<BestSeller>
    suspend fun getProduct(): ProductModel
    suspend fun getMyCardProd(): BasketModel
}
