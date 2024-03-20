package gr.gkortsaridis.mobilecomputingdemolab08

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Expense::class], version = 1)
abstract class ExpensesDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpensesDao

    companion object {
        @Volatile
        private var INSTANCE: ExpensesDatabase? = null
        fun getInstance(context: Context): ExpensesDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ExpensesDatabase::class.java,
                    "expenses_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }

}