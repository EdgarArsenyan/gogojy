package presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gogojy.R
import domain.model.basket.Basket
import domain.model.basket.BasketData

class CardAdapter : RecyclerView.Adapter<CardVH>() {

    private var listOfBaskets = arrayListOf<Basket>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardVH {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.item_my_cart_layout, parent, false)
        return CardVH(item)
    }

    override fun onBindViewHolder(holder: CardVH, position: Int) {
        holder.apply {
            bind(listOfBaskets[position])
        }
    }

    fun setItems(models: List<Basket>) {
        listOfBaskets.clear()
        listOfBaskets.addAll(models)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listOfBaskets.size
}
