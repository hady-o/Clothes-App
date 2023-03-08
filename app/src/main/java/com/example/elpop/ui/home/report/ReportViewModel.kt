package com.example.elpop.ui.home.report

import android.app.Application
import androidx.lifecycle.*
import com.example.elpop.clothes.ClotheDao
import com.example.elpop.data.Clothe
import com.example.elpop.data.Report
import kotlinx.coroutines.launch

class ReportViewModel(application: Application) : AndroidViewModel(application) {
    private val database = ClotheDao.ClotheRoomDataBase.getInstance(application)
    private val _allReports = MutableLiveData<List<Report>>()
    val allReports: LiveData<List<Report>> = _allReports

    private val _report = MutableLiveData<Report>()
    val report: LiveData<Report> = _report

    private val _allMoney = MutableLiveData<Double>()
    val allMoney: LiveData<Double> = _allMoney


    init {
            getAll("ex")
    }

    fun getAll(type: String) {
        viewModelScope.launch {
            _allReports.value=database.dao.getAllReports(type)
        }
    }

    fun addReport(report: Report) {
        viewModelScope.launch {
           database.dao.insertReport(report)
        }
    }

    fun getAllMoney() {
        var t =0.0
        viewModelScope.launch {
            for (r in _allReports.value!!){
                t+=r.price
            }
            _allMoney.value=t
        }
    }

    fun deleteReport(id:Int) {
        viewModelScope.launch {
            database.dao.deleteReport(id)
        }
    }

    fun getReportById(id:Int) {
        viewModelScope.launch {
           _report.value = database.dao.getReport(id)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            database.dao.deleteAllRepost()
        }
    }

}