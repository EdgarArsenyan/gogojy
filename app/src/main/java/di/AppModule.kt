package di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import presentation.MainViewModel

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModel(
            getHotSalesUseCase = get(),
            getBestSellerUseCase = get(),
            getProductUseCase = get(),
            getMyCardProdUseCase = get()
        )
    }
}