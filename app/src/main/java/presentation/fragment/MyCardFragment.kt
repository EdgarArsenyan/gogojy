package presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.data.util.GetCorrectValue.Companion.checkValue
import com.example.gogojy.R
import domain.model.basket.Basket
import domain.model.basket.BasketData
import domain.model.basket.BasketModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.my_card_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import presentation.MainViewModel
import presentation.adapter.CardAdapter

class MyCardFragment : Fragment(R.layout.my_card_layout) {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var cardAdapter: CardAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadMyCardProd()

        viewModel.myCardResult.observe(viewLifecycleOwner) {
            initView(it)
            initPhoneRecyclerView(it.basket as MutableList<Basket>)
        }
    }

    private fun initPhoneRecyclerView(basketList: MutableList<Basket>) {
        cardAdapter = CardAdapter()
        rvMyCards.adapter = cardAdapter
        val linearLayoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        rvMyCards.layoutManager = linearLayoutManager
        rvMyCards.setHasFixedSize(true)
        cardAdapter.setItems(basketList)
    }

    @SuppressLint("SetTextI18n")
    private fun initView(basketModel: BasketModel){
        tvUs.text = "${checkValue(basketModel.total)} us"
        tvFree.text = basketModel.delivery
    }
}