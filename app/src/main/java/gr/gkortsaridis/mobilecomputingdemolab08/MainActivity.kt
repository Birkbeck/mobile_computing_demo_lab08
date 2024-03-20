package gr.gkortsaridis.mobilecomputingdemolab08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import gr.gkortsaridis.mobilecomputingdemolab08.databinding.ActivityMainBinding
import gr.gkortsaridis.mobilecomputingdemolab08.databinding.ExpenseDialogBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ExpenseAdapter()
        binding.recyclerView.adapter = adapter
        binding.addButton.setOnClickListener { showAddExpenseDialog(null) }

        val dao = ExpensesDatabase.getInstance(applicationContext).expenseDao()
        viewModel.expensesDao = dao
        viewModel.readAllExpenses()
        viewModel.expenses.observe(this) { expenses ->
            adapter.updateExpenses(expenses)
        }

    }

    private fun showAddExpenseDialog(expense: Expense?) {
        val dialogBinding = ExpenseDialogBinding.inflate(layoutInflater)
        expense?.let {
            dialogBinding.titleEditText.setText(it.title)
            dialogBinding.costEditText.setText(it.cost.toString())
        }

        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.saveButton.setOnClickListener {
            // Get input from the EditText fields
            val title = dialogBinding.titleEditText.text.toString()
            val cost = dialogBinding.costEditText.text.toString().toDoubleOrNull() ?: 0.0
            viewModel.addExpense(title,cost)
            dialog.dismiss()
        }

        dialog.show()
    }
}