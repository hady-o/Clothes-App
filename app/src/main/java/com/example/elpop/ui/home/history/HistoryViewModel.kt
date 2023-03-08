package com.example.elpop.ui.home.history

import android.app.Application
import androidx.lifecycle.*
import com.example.elpop.clothes.ClotheDao
import com.example.elpop.data.History
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val database = ClotheDao.ClotheRoomDataBase.getInstance(application)
    private val _allHistoryData = MutableLiveData<List<History>>()
    val allHistoryData: LiveData<List<History>> = _allHistoryData

    private val _allMoney = MutableLiveData<Double>()
    val allMoney: LiveData<Double> = _allMoney

    private val _history = MutableLiveData<History>()
    val history: LiveData<History> = _history


    init {
            getAllHistory()
    }

    fun getAllHistory() {
        viewModelScope.launch {
            _allHistoryData.value = database.dao.getHistory()
        }
    }

    fun getHistoryByDate(date:String) {
        viewModelScope.launch {
            _allHistoryData.value = database.dao.getHistoryByDate(date)
        }
    }
    fun getHistoryById(id:Int) {
        viewModelScope.launch {
            _history.value = database.dao.getHistoryById(id)
        }
    }
    fun updateHistory(total:Double,id:Int) {
        viewModelScope.launch {
            database.dao.updateHistory(total, id)
        }
    }

    fun getTotalMoney() {
        var t =0.0
        viewModelScope.launch {
           for (h in _allHistoryData.value!!){
               t+=h.total
           }
            _allMoney.value=t
        }
    }

    fun addToHistory(history: History):Long {
            return database.dao.insertHistory(history)
    }
}