package domain.model.main

import domain.model.main.BestSeller
import domain.model.main.HomeStore

data class MainModel(
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)