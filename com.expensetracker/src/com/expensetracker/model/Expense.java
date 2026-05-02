package com.expensetracker.model;


public class Expense {
    private String description;
    private double amount;
    private String category;

    // Constructor
    public Expense(String description, double amount, String category) {
        if (description == null || description.isEmpty() ||
            category == null || category.isEmpty() ||
            amount <= 0) {
            throw new IllegalArgumentException("Invalid expense data.");
        }
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    // Convert to CSV
    public String toCSV() {
        return description + "," + amount + "," + category;
    }

    // Parse CSV to object
    public static Expense parseExpense(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid CSV format");
        }
        return new Expense(
                parts[0],
                Double.parseDouble(parts[1]),
                parts[2]
        );
    }

    // Getters
    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return description + " - " + amount + " (" + category + ")";
    }
}
