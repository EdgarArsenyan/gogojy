package domain.model.basket

import domain.model.basket.Basket

data class BasketModel(
    val basket: List<Basket>,
    val delivery: String,
    val id: String,
    val total: Int
)