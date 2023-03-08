package com.example.elpop.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Report(
    var name : String,
    var type : String,
    var price: Double,
    var date : String
) : Parcelable{
    @PrimaryKey(autoGenerate = true)
    var reportId:Int=0
}
