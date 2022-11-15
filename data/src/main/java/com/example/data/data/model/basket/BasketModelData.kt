package domain.model.basket

data class BasketModelData(
    val basket: List<BasketData>,
    val delivery: String,
    val id: String,
    val total: Int
)