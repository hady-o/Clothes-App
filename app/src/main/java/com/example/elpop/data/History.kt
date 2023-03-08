package com.example.elpop.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(

   var total:Double,
   var date : String
){
   @PrimaryKey(autoGenerate = true)
   var historyId:Int =0
}