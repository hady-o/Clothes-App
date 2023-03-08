package com.example.elpop.ui.home.person

import android.app.Application
import androidx.lifecycle.*
import com.example.elpop.clothes.ClotheDao
import com.example.elpop.data.Clothe
import com.example.elpop.data.Person
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {
    private val database = ClotheDao.ClotheRoomDataBase.getInstance(application)
    private val _allPersons = MutableLiveData<List<Person>>()
    val allPersons: LiveData<List<Person>> = _allPersons

    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person> = _person

    init {
            getAllPerson()
    }

    fun getAllPerson() {
        viewModelScope.launch {
            _allPersons.value = database.dao.getAllPersons()
        }
    }

    fun getPersonById(id:Int) {
       viewModelScope.launch {
           _person.value = database.dao.getPerson(id)
       }
    }

    fun addPerson(person: Person) {
        viewModelScope.launch {
            database.dao.insertPerson(person)
        }
    }

    fun updatePersonRest(id: Int,rest:Double) {
        viewModelScope.launch {
            database.dao.updatePersonRest(id,rest)
        }
    }
    fun updatePerson(id: Int,name:String ,phone:String ,salary:Double) {
        viewModelScope.launch {
            database.dao.updatePerson(id,name,phone,salary)
        }
    }

    fun deletePerson(id: Int) {
        viewModelScope.launch {
            database.dao.deletePerson(id)
        }
    }

}