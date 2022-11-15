package presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.data.data.util.GetCorrectValue.Companion.checkValue
import domain.model.basket.Basket
import kotlinx.android.synthetic.main.item_my_cart_layout.view.*


class CardVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(model: Basket) {
        itemView.tvCartProductName.text = model.title
        itemView.tvProdValue.text = checkValue(model.price)
        Glide.with(itemView.context)
            .load(model.images)
            .apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(16)))
            .into(itemView.ivCartPhoneImage)
    }
}
