package com.angelstudios.mystore.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.angelstudios.core.domain.product.Product
import com.angelstudios.mystore.domain.product.ProductEntity


@Dao
interface ProductDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM PRODUCT WHERE companyId = :companyId")
    suspend fun getProductsForCompany(companyId: String): List<Product>

}