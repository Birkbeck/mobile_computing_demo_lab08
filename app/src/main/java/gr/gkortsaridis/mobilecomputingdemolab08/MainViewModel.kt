package gr.gkortsaridis.mobilecomputingdemolab08

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _expenses = MutableLiveData(listOf<Expense>())
    val expenses: LiveData<List<Expense>> = _expenses


    fun readAllExpenses() {
        //TODO: Read all saved Expenses
    }

    fun addExpense(title: String, cost: Double) {
        //TODO: Create and save Expense
    }

    fun editExpense(title: String, cost: Double) {
        //TODO: Edit and replace Expense
    }

    fun deleteExpense(expense: Expense) {
        //TODO: Delete Expense
    }
}