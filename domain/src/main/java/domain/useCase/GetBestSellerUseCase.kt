package domain.useCase

import domain.model.main.BestSeller
import domain.repo.ApiRepository

class GetBestSellerUseCase(private val apiRepo: ApiRepository) {

    suspend fun execute(): MutableList<BestSeller> = apiRepo.getBestSeller()

}
