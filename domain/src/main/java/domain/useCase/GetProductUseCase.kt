package domain.useCase

import domain.model.ProductModel
import domain.repo.ApiRepository

class GetProductUseCase (private val apiRepo: ApiRepository) {

    suspend fun execute(): ProductModel? = apiRepo.getProduct()

}