package presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gogojy.R
import domain.model.main.BestSeller
import domain.model.main.BestSellerData

class PhoneAdapter(private val listener: ItemListener) : RecyclerView.Adapter<PhoneVH>() {
    interface ItemListener {
        fun onClick()
        fun onLongClick()
    }

    private var listOfBestSellers = arrayListOf<BestSeller>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneVH {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.item_phone_layout, parent, false)
        return PhoneVH(item)
    }

    override fun onBindViewHolder(holder: PhoneVH, position: Int) {
        holder.apply {
            bind(listOfBestSellers[position])
            itemView.setOnClickListener { listener.onClick() }
        }
    }

    fun setItems(models: List<BestSeller>) {
        listOfBestSellers.clear()
        listOfBestSellers.addAll(models)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listOfBestSellers.size

}
