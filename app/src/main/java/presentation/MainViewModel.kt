package presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.ProductModel
import domain.model.ProductModelData
import domain.model.basket.BasketModel
import domain.model.basket.BasketModelData
import domain.model.main.BestSeller
import domain.model.main.BestSellerData
import domain.model.main.HomeStore
import domain.model.main.HomeStoreData
import domain.useCase.GetBestSellerUseCase
import domain.useCase.GetHotSalesUseCase
import domain.useCase.GetMyCardProdUseCase
import domain.useCase.GetProductUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getHotSalesUseCase: GetHotSalesUseCase,
    private val getBestSellerUseCase: GetBestSellerUseCase,
    private val getProductUseCase: GetProductUseCase,
    private val getMyCardProdUseCase: GetMyCardProdUseCase
    ): ViewModel() {

    private val homeData = MutableLiveData<MutableList<HomeStore>>()
    private val bestSellerData = MutableLiveData<MutableList<BestSeller>>()
    private val productData = MutableLiveData<ProductModel>()
    private val myCardData = MutableLiveData<BasketModel>()

    val homeResult: LiveData<MutableList<HomeStore>> = homeData
    val bestSellerResult: LiveData<MutableList<BestSeller>> = bestSellerData
    val productResult: LiveData<ProductModel> = productData
    val myCardResult: LiveData<BasketModel> = myCardData

     fun loadHotList(){
        viewModelScope.launch {
            homeData.value = getHotSalesUseCase.execute()
        }

    }

    fun loadBestList(){
        viewModelScope.launch {
            bestSellerData.value = getBestSellerUseCase.execute()
        }

    }

    fun loadProduct(){
        viewModelScope.launch {
            productData.value = getProductUseCase.execute()
        }
    }

    fun loadMyCardProd(){
        viewModelScope.launch {
            myCardData.value = getMyCardProdUseCase.execute()
        }
    }

}