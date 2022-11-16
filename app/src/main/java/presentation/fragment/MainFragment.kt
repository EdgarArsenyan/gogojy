package presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.data.data.util.AppConstants.brandRangeArray
import com.example.data.data.util.AppConstants.priceRangeArray
import com.example.data.data.util.AppConstants.sizeRangeArray
import com.example.gogojy.R
import domain.model.main.BestSeller
import domain.model.main.HomeStore
import kotlinx.android.synthetic.main.filter_layout.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import presentation.MainActivity.Companion.isFirstRun
import presentation.MainViewModel
import presentation.adapter.PhoneAdapter

class MainFragment: Fragment(R.layout.fragment_main) {

    private lateinit var phoneAdapter: PhoneAdapter
    private var bestSellerList: MutableList<BestSeller> = mutableListOf()
    private var count = 0
    private var isMainFragment = false

    private val viewModel by viewModel<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isMainFragment = true
        initViewModel()
        checkFirsRun()
        clickHandleFromMainFrag()
    }

    private fun initViewModel(){
        viewModel.loadHotList()

        viewModel.loadBestList()
        viewModel.bestSellerResult.observe(viewLifecycleOwner) {
            bestSellerList = it
            initPhoneRecyclerView()
        }

    }

    private fun initSpinners() {
        val priceArrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, priceRangeArray)
        val brandArrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, brandRangeArray)
        val sizeArrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, sizeRangeArray)
        spPrice.adapter = priceArrayAdapter
        spBrand.adapter = brandArrayAdapter
        spSize.adapter = sizeArrayAdapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.homeResult.observe(viewLifecycleOwner) {
            saleLoop(it)
        }
    }

    private fun saleLoop(hotSaleList: MutableList<HomeStore>){
        if(isMainFragment){
            if(count == hotSaleList.size - 1){
                count = 0
            }else ++count
            initSaleView(hotSaleList[count])
            Handler().postDelayed({
                saleLoop(hotSaleList)
            },3000)
        }else return
    }

    private fun checkFirsRun() {
        if(isFirstRun){
            laySplash.isVisible = true
            Handler().postDelayed({
                laySplash.isVisible = false
                isFirstRun = false
            },2000)
        }
    }

    private fun initPhoneRecyclerView() {

        phoneAdapter = PhoneAdapter(object : PhoneAdapter.ItemListener {
            override fun onClick() {
                val action = MainFragmentDirections.actionMainFragmentToProductDetailsFragment()
                findNavController().navigate(action)
                isMainFragment = false
                viewModelStore.clear()
                NavController(requireContext()).popBackStack(R.id.mainFragment, true)
            }
        })

        rvPhones.adapter = phoneAdapter
        val linearLayoutManager = GridLayoutManager(context, 2).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        rvPhones.layoutManager = linearLayoutManager
        rvPhones.setHasFixedSize(true)
        phoneAdapter.setItems(bestSellerList)
    }

    private fun initSaleView(hotSaleModel: HomeStore){
        ivPhone.isVisible = hotSaleModel.is_new
        tvNew.isVisible = hotSaleModel.is_new
        tvPhoneName.text = hotSaleModel.title
        tvTitlePhone.text = hotSaleModel.subtitle
        Glide.with(requireContext())
            .load(hotSaleModel.picture)
            .into(ivSalePhone)
    }

    private fun clickHandleFromMainFrag(){
        clickToCategoryItems()
        clickToFilter()
        hideFilter()
    }

    private fun hideFilter(){
        ivDone.setOnClickListener {
            layFilter.isVisible = false
        }
    }

    private fun clickToFilter() {
        ivFilter.setOnClickListener {
            if(!layFilter.isVisible) {
                layFilter.isVisible = true
                initSpinners()
            } else return@setOnClickListener
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun clickToCategoryItems(){
        layProdPhone.setOnClickListener {

            ivProdPhone.background = resources.getDrawable(R.drawable.ic_shape_orange)
            tvProdPhone.setTextColor(resources.getColor(R.color.orange))
            ivProdPhone.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))

            ivProdComp.background = resources.getDrawable(R.drawable.ic_shape_passive)
            tvProdComp.setTextColor(resources.getColor(R.color.black))
            ivProdComp.setColorFilter(ContextCompat.getColor(requireContext(), R.color.prod_icons_passive))

            ivProdHealth.background = resources.getDrawable(R.drawable.ic_shape_passive)
            tvProdHealt.setTextColor(resources.getColor(R.color.black))
            ivProdHealth.setColorFilter(ContextCompat.getColor(requireContext(), R.color.prod_icons_passive))

            ivProdBooks.background = resources.getDrawable(R.drawable.ic_shape_passive)
            tvProdBook.setTextColor(resources.getColor(R.color.black))
            ivProdBooks.setColorFilter(ContextCompat.getColor(requireContext(), R.color.prod_icons_passive))
        }

        layProdComp.setOnClickListener {

            ivProdPhone.background = resources.getDrawable(R.drawable.ic_shape_passive)
            tvProdPhone.setTextColor(resources.getColor(R.color.black))
            ivProdPhone.setColorFilter(ContextCompat.getColor(requireContext(), R.color.prod_icons_passive))

            ivProdComp.background = resources.getDrawable(R.drawable.ic_shape_orange)
            tvProdComp.setTextColor(resources.getColor(R.color.orange))
            ivProdComp.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))

            ivProdHealth.background = resources.getDrawable(R.drawable.ic_shape_passive)
            tvProdHealt.setTextColor(resources.getColor(R.color.black))
            ivProdHealth.setColorFilter(ContextCompat.getColor(requireContext(), R.color.prod_icons_passive))

            ivProdBooks.background = resources.getDrawable(R.drawable.ic_shape_passive)
            tvProdBook.setTextColor(resources.getColor(R.color.black))
            ivProdBooks.setColorFilter(ContextCompat.getColor(requireContext(), R.color.prod_icons_passive))
        }

        layProdHealth.setOnClickListener {

            ivProdPhone.background = resources.getDrawable(R.drawable.ic_shape_passive)
            tvProdPhone.setTextColor(resources.getColor(R.color.black))
            ivProdPhone.setColorFilter(ContextCompat.getColor(requireContext(), R.color.prod_icons_passive))

            ivProdComp.background = resources.getDrawable(R.drawable.ic_shape_passive)
            tvProdComp.setTextColor(resources.getColor(R.color.black))
            ivProdComp.setColorFilter(ContextCompat.getColor(requireContext(), R.color.prod_icons_passive))

            ivProdHealth.background = resources.getDrawable(R.drawable.ic_shape_orange)
            tvProdHealt.setTextColor(resources.getColor(R.color.orange))
            ivProdHealth.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))

            ivProdBooks.background = resources.getDrawable(R.drawable.ic_shape_passive)
            tvProdBook.setTextColor(resources.getColor(R.color.black))
            ivProdBooks.setColorFilter(ContextCompat.getColor(requireContext(), R.color.prod_icons_passive))
        }

        layProdBooks.setOnClickListener {

            ivProdPhone.background = resources.getDrawable(R.drawable.ic_shape_passive)
            tvProdPhone.setTextColor(resources.getColor(R.color.black))
            ivProdPhone.setColorFilter(ContextCompat.getColor(requireContext(), R.color.prod_icons_passive))

            ivProdComp.background = resources.getDrawable(R.drawable.ic_shape_passive)
            tvProdComp.setTextColor(resources.getColor(R.color.black))
            ivProdComp.setColorFilter(ContextCompat.getColor(requireContext(), R.color.prod_icons_passive))

            ivProdHealth.background = resources.getDrawable(R.drawable.ic_shape_passive)
            tvProdHealt.setTextColor(resources.getColor(R.color.black))
            ivProdHealth.setColorFilter(ContextCompat.getColor(requireContext(), R.color.prod_icons_passive))

            ivProdBooks.background = resources.getDrawable(R.drawable.ic_shape_orange)
            tvProdBook.setTextColor(resources.getColor(R.color.orange))
            ivProdBooks.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))
        }
    }
}