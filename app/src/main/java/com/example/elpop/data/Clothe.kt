package com.example.elpop.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
@Entity()
 data class Clothe(
 var clothName: String,
 var type:String,
 var number: Int,
 var price: Double) : Parcelable{
 @PrimaryKey(autoGenerate = true)
 var id: Int =0
 }


