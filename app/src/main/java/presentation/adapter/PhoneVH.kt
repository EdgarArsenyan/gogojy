package presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.data.util.GetCorrectValue.Companion.checkValue
import com.example.gogojy.R
import domain.model.main.BestSeller
import domain.model.main.BestSellerData
import kotlinx.android.synthetic.main.item_phone_layout.view.*

class PhoneVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(model: BestSeller) {
        itemView.tvValue.text = checkValue(model.discount_price)
        itemView.tvProdValue.text = checkValue(model.price_without_discount)
        itemView.tvSamsung_gal.text = model.title
//        if(model.is_favorites) itemView.ivFavIcon.setImageResource(R.drawable.ic_fav)
        Glide.with(itemView.context)
            .load(model.picture)
            .into(itemView.samsung_gal)
    }
}
