package com.frame_application.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.frame_application.model.Item

@Dao
interface RoomDao {

    @Query("SELECT * FROM items")
    fun getAllItems(): LiveData<List<Item>>

    @Query("SELECT * FROM items WHERE id == :id")
    fun getItemByID(id: Long?): Item

    @Insert
    fun insertItem(item: Item)

    @Update
    fun updateItem(item: Item)

    @Delete
    fun deleteItem(item: Item)

    @Query("DELETE FROM items")
    fun deleteAll()

}