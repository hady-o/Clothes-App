package com.example.elpop.ui.home.cart

import android.app.Application
import androidx.lifecycle.*
import com.example.elpop.clothes.ClotheDao
import com.example.elpop.data.Cart
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val database = ClotheDao.ClotheRoomDataBase.getInstance(application)
    private val _allCart = MutableLiveData<List<Cart>>()
    val allCart: LiveData<List<Cart>> = _allCart

    private var _total = MutableLiveData<Double>()
    val total: LiveData<Double> = _total


    init {
            getCart()
        getTotal()
    }

    fun getCart() {
        viewModelScope.launch {
            _allCart.value = database.dao.getCartItems()
        }
    }

    fun addToCart(cart: Cart) {
        viewModelScope.launch {
            database.dao.insertToCart(cart)
        }
    }
    fun deleteCart() {
        viewModelScope.launch {
            database.dao.deleteCart()
        }
    }
    fun deleteFromCart(id:Int) {
        viewModelScope.launch {
            database.dao.deleteFromCart(id)
        }
    }

    fun updateCart(num:Int,total:Double,id:Int) {
        viewModelScope.launch {
            database.dao.updateCart(num,total,id)
        }
    }

    fun getTotal(){
        var t =0.0
        var l =_allCart.value
        for (c in l!!){
            t+=c.total
        }
        _total.value=t
    }
    fun getCartById(id:Int):Cart{
        return database.dao.getCartById(id)
    }


}