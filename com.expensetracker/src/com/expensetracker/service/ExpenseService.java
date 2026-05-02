package com.expensetracker.service;

import com.expensetracker.model.Expense;
import com.expensetracker.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class ExpenseService {

    private List<Expense> expenses = new ArrayList<>();

    // Load expenses at startup
    public void loadExpenses() {
        expenses = FileUtil.readFromFile();
    }

    // Save expenses
    public void saveExpenses() {
        FileUtil.writeToFile(expenses);
    }

    // Add expense
    public void addExpense(Expense expense) {
        expenses.add(expense);
        saveExpenses();
    }

    // Get all
    public List<Expense> getAllExpenses() {
        return expenses;
    }

    // Filter by category
    public List<Expense> getExpensesByCategory(String category) {
        List<Expense> filtered = new ArrayList<>();
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                filtered.add(e);
            }
        }
        return filtered;
    }

    // Total calculation
    public double calculateTotalExpenses() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        return total;
    }
}