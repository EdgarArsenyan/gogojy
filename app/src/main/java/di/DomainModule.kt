package di

import domain.useCase.GetBestSellerUseCase
import domain.useCase.GetHotSalesUseCase
import domain.useCase.GetMyCardProdUseCase
import domain.useCase.GetProductUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetHotSalesUseCase> {
        GetHotSalesUseCase(apiRepo = get() )
    }

    factory<GetBestSellerUseCase> {
        GetBestSellerUseCase(apiRepo = get() )
    }

    factory<GetProductUseCase> {
        GetProductUseCase(apiRepo = get() )
    }

    factory<GetMyCardProdUseCase> {
        GetMyCardProdUseCase(apiRepo = get() )
    }

}