package com.example.kmptraining.migrate.session5.session5_3.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class ProductFlowKey : NavKey {
    @Serializable
    data object CategoryList : ProductFlowKey()

    @Serializable
    data class ProductList(val categoryId: Int) : ProductFlowKey()

    @Serializable
    data class ProductDetail(val productId: Int, val categoryId: Int) : ProductFlowKey()

    @Serializable
    data object Cart : ProductFlowKey()
}