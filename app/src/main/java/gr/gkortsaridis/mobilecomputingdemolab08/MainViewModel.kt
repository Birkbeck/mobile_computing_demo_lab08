package gr.gkortsaridis.mobilecomputingdemolab08

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _expenses = MutableLiveData(listOf<Expense>())
    val expenses: LiveData<List<Expense>> = _expenses

    var expensesDao: ExpensesDao? = null

    fun readAllExpenses() {
        viewModelScope.launch {
            expensesDao?.let {
                val expenses = it.getAllExpenses()

                Log.i("BBK", expenses.toString())
                _expenses.value = expenses
            }
        }
    }

    fun addExpense(title: String, cost: Double) {
        viewModelScope.launch {
            expensesDao?.let {
                val expense = Expense(title = title, cost = cost)
                it.insertExpense(expense)

                readAllExpenses()
            }
        }

    }

    fun editExpense(expense: Expense) {
        viewModelScope.launch {
            expensesDao?.let {
                it.updateExpense(expense)

                readAllExpenses()
            }
        }

    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            expensesDao?.let {
                it.deleteExpense(expense)

                readAllExpenses()
            }
        }

    }
}