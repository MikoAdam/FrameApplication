package com.frame_application.data

import androidx.lifecycle.LiveData
import com.frame_application.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val roomDao: RoomDao) {

    fun getAllItems(): LiveData<List<Item>> {
        return roomDao.getAllItems()
    }

    suspend fun getItemByID(itemID: Long)  = withContext(Dispatchers.IO) {
         roomDao.getItemByID(itemID)
    }

    suspend fun insert(item: Item) = withContext(Dispatchers.IO) {
        roomDao.insertItem(item)
    }

    suspend fun delete(item: Item) = withContext(Dispatchers.IO) {
        roomDao.deleteItem(item)
    }

    suspend fun update(item: Item) = withContext(Dispatchers.IO) {
        roomDao.updateItem(item)
    }

    suspend fun deleteAll() = withContext((Dispatchers.IO)) {
        roomDao.deleteAll()
    }
}