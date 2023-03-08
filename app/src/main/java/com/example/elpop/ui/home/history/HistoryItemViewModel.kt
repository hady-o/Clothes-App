package com.example.elpop.ui.home.history

import android.app.Application
import androidx.lifecycle.*
import com.example.elpop.clothes.ClotheDao
import com.example.elpop.data.HistoryItem
import kotlinx.coroutines.launch

class HistoryItemViewModel(application: Application) : AndroidViewModel(application) {
    private val database = ClotheDao.ClotheRoomDataBase.getInstance(application)
    private val _allItems = MutableLiveData<List<HistoryItem>>()
    val allItems: LiveData<List<HistoryItem>> = _allItems




    fun getAllItems(id:Int) {
        viewModelScope.launch {
            _allItems.value = database.dao.getHistoryItems(id)
        }
    }
    fun updateItem(id:Int,num:Int) {
        viewModelScope.launch {
            database.dao.updateHistoryItem(num, id)
        }
    }

    fun deleteItem(id:Int) {
        viewModelScope.launch {
            database.dao.deleteFromHistory(id)
        }
    }

    fun addToHistoryItems(item: HistoryItem) {
             database.dao.insertHistoryItem(item)
    }
}