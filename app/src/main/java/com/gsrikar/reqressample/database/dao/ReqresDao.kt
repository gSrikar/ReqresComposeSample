package com.gsrikar.reqressample.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gsrikar.reqressample.models.ReqresData


/**
 * Interface acts as a bridge to the database layer
 */
@Dao
interface ReqresDao {

    /**
     * Insert all the items
     */
    @Insert
    suspend fun insertAll(list: List<ReqresData>)

    /**
     * Insert the data to the table
     */
    @Insert
    suspend fun insert(data: ReqresData)

    /**
     * Query the table for all the items
     */
    @Query("SELECT * from reqres")
    suspend fun queryAll(): List<ReqresData>

    /**
     * Delete the data in the table
     */
    @Delete
    suspend fun delete(data: ReqresData)
}