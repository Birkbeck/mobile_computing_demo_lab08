package gr.gkortsaridis.mobilecomputingdemolab08

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExpensesDao {
    @Query("SELECT * from Expenses")
    suspend fun getAllExpenses(): List<Expense>

    @Insert
    suspend fun insertExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)
}