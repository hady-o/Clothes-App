package com.example.elpop.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class HistoryItem(
    var id:Int,var name:String , var price:Double,var quantity:Int,var clotheId:Int,var clotheType:String) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var hId:Int=0
}