package gr.gkortsaridis.mobilecomputingdemolab08

import java.io.Serializable

data class Expense(
    val title: String,
    val cost: Double
): Serializable