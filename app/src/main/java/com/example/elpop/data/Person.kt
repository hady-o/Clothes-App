package com.example.elpop.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Person(var name:String, var phone:String, var salary:Double, var rest:Double) :
    Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}