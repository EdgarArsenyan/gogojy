package presentation.fragment

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.data.data.util.GetCorrectValue.Companion.checkValue
import com.example.gogojy.R
import domain.model.ProductModel
import kotlinx.android.synthetic.main.detail_product_layout.*
import kotlinx.android.synthetic.main.item_phone_layout.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import presentation.MainActivity.Companion.basketCount
import presentation.MainViewModel

class ProductDetailsFragment: Fragment(R.layout.detail_product_layout) {

    private val viewModel by viewModel<MainViewModel>()


    @SuppressLint("UseCompatLoadingForColorStateLists")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadProduct()

        viewModel.productResult.observe(viewLifecycleOwner){
            initView(it)
        }

        clickHandle()
    }

    private fun initView(productModel: ProductModel){

        ivCount.isVisible = basketCount > 0
        tvFavCardCount.isVisible = basketCount > 0
        tvFavCardCount.text = if(basketCount > 0) basketCount.toString() else ""
        tvProduct.text = productModel.title
        tvChip.text = productModel.sd
        tvCpu.text = productModel.CPU
        tvCamera.text = productModel.camera
        tvMinCapacity.text = "${productModel.capacity[0]} GB"
        tvMaxCapacity.text = "${productModel.capacity[1]} GB"
        tvValue.text = "${checkValue(productModel.price)}.00"
        tvExternal.text = productModel.capacity.last()
        ivProdColor1.backgroundTintList = ColorStateList.valueOf(Color.parseColor(productModel.color[0]))
        ivProdColor2.backgroundTintList = ColorStateList.valueOf(Color.parseColor(productModel.color[1]))
        Glide.with(requireContext())
            .load(productModel.images[0])
            .into(ivProduct)
    }

    private fun clickHandle(){
        ivProdColor1.setOnClickListener{
            ivChecked1.isVisible = true
            ivChecked2.isVisible = false
        }

        ivProdColor2.setOnClickListener{
            ivChecked2.isVisible = true
            ivChecked1.isVisible = false
        }

        tvMaxCapacity.setOnClickListener{
            ivMaxCapacity.isVisible = true
            ivMinCapacity.isVisible = false
            tvMaxCapacity.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            tvMinCapacity.setTextColor(ContextCompat.getColor(requireContext(), R.color.brown))
        }

        tvMinCapacity.setOnClickListener {
            ivMinCapacity.isVisible = true
            ivMaxCapacity.isVisible = false
            tvMinCapacity.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            tvMaxCapacity.setTextColor(ContextCompat.getColor(requireContext(), R.color.brown))
        }

        ivBasket.setOnClickListener {
            val action = ProductDetailsFragmentDirections.actionProductDetailsFragmentToMyCardFragment()
            findNavController().navigate(action)
            viewModelStore.clear()
        }

        ivAddCard.setOnClickListener {
            ++basketCount
            ivCount.isVisible = true
            tvFavCardCount.isVisible = true
            tvFavCardCount.text = basketCount.toString()
            ivAddCard.isClickable = false
        }
    }
}