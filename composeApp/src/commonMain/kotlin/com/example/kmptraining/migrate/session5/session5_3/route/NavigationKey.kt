package com.example.kmptraining.migrate.session5.session5_3.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class ProductFlowKey : NavKey {
    @Serializable
    data object CategoryList : com.example.kmptraining.migrate.session5.session5_3.route.ProductFlowKey()

    @Serializable
    data class ProductList(val categoryId: Int) : com.example.kmptraining.migrate.session5.session5_3.route.ProductFlowKey()

    @Serializable
    data class ProductDetail(val productId: Int, val categoryId: Int) : com.example.kmptraining.migrate.session5.session5_3.route.ProductFlowKey()

    @Serializable
    data object Cart : com.example.kmptraining.migrate.session5.session5_3.route.ProductFlowKey()
}