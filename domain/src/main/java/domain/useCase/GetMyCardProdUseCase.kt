package domain.useCase

import domain.model.ProductModel
import domain.model.basket.Basket
import domain.model.basket.BasketModel
import domain.repo.ApiRepository

class GetMyCardProdUseCase(private val apiRepo: ApiRepository){

    suspend fun execute(): BasketModel? = apiRepo.getMyCardProd()

}