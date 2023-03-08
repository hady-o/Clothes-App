package com.example.elpop.ui.home.userhome

import android.app.Application
import androidx.lifecycle.*
import com.example.elpop.clothes.ClotheDao
import com.example.elpop.data.Clothe
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val database = ClotheDao.ClotheRoomDataBase.getInstance(application)
    private val _allClothes = MutableLiveData<List<Clothe>>()
    val allClothes: LiveData<List<Clothe>> = _allClothes


    init {
            getClothe("cloth")
    }

    fun getClothe(type: String) {
        viewModelScope.launch {
            _allClothes.value = database.dao.getClothe(type)
        }
    }

    fun updateClothe(num:Int,id:Int) {
        viewModelScope.launch {
           database.dao.updateClothe(num,id)
        }
    }

    fun updateClothe(num:Int,name:String,price:Double,id:Int) {
        viewModelScope.launch {
            database.dao.updateClothe(name,price,num,id)
        }
    }

    fun getClotheById(id:Int):Clothe {
        return database.dao.getClotheById(id)
    }

    fun addClothe(clothe: Clothe) {
        viewModelScope.launch {
            database.dao.insertClothe(clothe)
        }
    }



    fun deleteClothe(id: Int) {
        viewModelScope.launch {
            database.dao.deleteClothe(id)
        }
    }

}