package com.example.elpop.clothes

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.elpop.data.*

import kotlinx.coroutines.flow.Flow


@Dao
interface ClotheDao {
    //clothe dao
    @Insert()
     fun insertClothe(clothe: Clothe)
    @Query("select * from Clothe where type =:type ")
     fun getClothe(type:String): List<Clothe>
    @Query("select * from Clothe where id =:id")
    fun getClotheById(id:Int): Clothe
    @Query("update Clothe set number =:num where id =:id ")
    fun updateClothe(num:Int,id:Int)
    @Query("update Clothe set number =:num,clothName=:name,price=:price where id =:id ")
    fun updateClothe(name: String,price:Double,num:Int,id:Int)
    @Query("delete from Clothe  where id =:id ")
    fun deleteClothe(id:Int)
    //cart dao
    @Insert()
    fun insertToCart(cart: Cart)
    @Query("select * from Cart")
    fun getCartItems(): List<Cart>
    @Query("delete from Cart")
    fun deleteCart()
    @Query("delete from Cart  where itemId =:id ")
    fun deleteFromCart(id:Int)
    @Query("select * from Cart where itemId =:id")
    fun getCartById(id:Int): Cart
    @Query("update Cart set itemQuantity =:num, total =:total where itemId =:id")
    fun updateCart(num:Int,total:Double,id:Int)
    //history item dao
    @Insert()
    fun insertHistoryItem(item: HistoryItem)
    @Query("select * from HistoryItem where id=:id")
    fun getHistoryItems(id:Int): List<HistoryItem>
    @Query("update HistoryItem set quantity =:num where hId =:id")
    fun updateHistoryItem(num:Int,id:Int)
    @Query("delete from HistoryItem  where hId =:id ")
    fun deleteFromHistory(id:Int)
    //history  dao
    @Insert()
    fun insertHistory(history: History):Long
    @Query("select * from History")
    fun getHistory(): List<History>
    @Query("select * from History where :date like date")
    fun getHistoryByDate(date:String): List<History>
    @Query("select * from History where historyId =:id ")
    fun getHistoryById(id:Int): History
    @Query("update History set total =:num where historyId =:id")
    fun updateHistory(num:Double,id:Int)
    //person dao
    @Insert()
    fun insertPerson(person: Person)
    @Query("select * from Person")
    fun getAllPersons(): List<Person>
    @Query("select * from Person where id=:id")
    fun getPerson(id:Int): Person
    @Query("delete from Person")
    fun deleteAllPersons()
    @Query("delete from Person where id =:id ")
    fun deletePerson(id:Int)
    @Query("update Person set name=:name,phone=:phone,salary=:salary where id=:id")
    fun updatePerson(id: Int,name: String,phone: String, salary: Double)
    @Query("update Person set rest =:rest  where id=:id")
    fun updatePersonRest(id: Int,rest: Double)
    //report dao
    @Insert()
    fun insertReport(report: Report)
    @Query("select * from Report where type=:type")
    fun getAllReports(type: String): List<Report>
    @Query("select * from Report where reportId=:id")
    fun getReport(id:Int): Report
    @Query("delete from Report")
    fun deleteAllRepost()
    @Query("delete from Report where reportId =:id ")
    fun deleteReport(id:Int)

    @Database(entities = [Clothe::class,Cart::class,History::class,HistoryItem::class,Person::class,Report::class ], version = 1, exportSchema = false)
    abstract class ClotheRoomDataBase: RoomDatabase() {
        abstract val dao : ClotheDao
        companion object {
            @Volatile
            private var Instance: ClotheRoomDataBase? = null
            fun getInstance(context: Context): ClotheRoomDataBase {
                synchronized(this)
                {
                    var instance = Instance
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            ClotheRoomDataBase::class.java,
                            "clothes_database"
                        )
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build()
                        Instance = instance
                    }
                    return instance
                }
            }
        }
    }
}