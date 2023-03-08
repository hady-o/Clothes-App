package com.example.elpop.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Cart(
    @PrimaryKey
    var itemId:Int,
    var itemQuantity:Int,
    var total:Double

) : Parcelable
