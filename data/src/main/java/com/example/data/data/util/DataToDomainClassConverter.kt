package com.example.data.data.util

import domain.model.ProductModel
import domain.model.ProductModelData
import domain.model.basket.Basket
import domain.model.basket.BasketData
import domain.model.basket.BasketModel
import domain.model.basket.BasketModelData
import domain.model.main.*

fun BasketData.toBasket(): Basket = Basket(
    id = id,
    images = images,
    price = price,
    title = title
)

fun BasketModelData.toBasketModel(): BasketModel = BasketModel(
    basket = basket.map { it.toBasket() },
    delivery = delivery,
    id = id,
    total = total
)

fun BestSellerData.toBestSeller(): BestSeller = BestSeller(
    discount_price = discount_price,
    id = id,
    is_favorites = is_favorites,
    picture = picture,
    price_without_discount = price_without_discount,
    title = title
)

fun HomeStoreData.toHomeStore(): HomeStore = HomeStore(
    id = id,
    is_buy = is_buy,
    is_new = is_new,
    picture = picture,
    subtitle = subtitle,
    title = title
)

fun MainModelData.toiMainModel(): MainModel = MainModel(
    best_seller = best_seller.map { it.toBestSeller() },
    home_store = home_store.map { it.toHomeStore() }
)

fun ProductModelData.toProductModel(): ProductModel = ProductModel(
    CPU= CPU,
    camera = camera,
    capacity = capacity,
    color = color,
    id = id,
    images = images,
    isFavorites = isFavorites,
            price = price,
    rating = rating,
    sd = sd,
    ssd = ssd,
    title = title
)