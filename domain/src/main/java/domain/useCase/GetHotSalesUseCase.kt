package domain.useCase

import domain.model.main.HomeStore
import domain.repo.ApiRepository

class GetHotSalesUseCase(private val apiRepo: ApiRepository) {
    suspend fun execute(): MutableList<HomeStore> = apiRepo.getHotSales()
}