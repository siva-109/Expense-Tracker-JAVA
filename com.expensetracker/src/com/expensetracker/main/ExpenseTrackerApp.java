package com.expensetracker.main;

import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ExpenseTrackerApp {

    private static ExpenseService service = new ExpenseService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        service.loadExpenses();

        while (true) {
            showMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                handleUserChoice(choice);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nExpense Tracker Menu:");
        System.out.println("1. Add Expense");
        System.out.println("2. View All Expenses");
        System.out.println("3. View Expenses by Category");
        System.out.println("4. Calculate Total Expenses");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                addExpense();
                break;
            case 2:
                viewAllExpenses();
                break;
            case 3:
                viewByCategory();
                break;
            case 4:
                calculateTotal();
                break;
            case 5:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void addExpense() {
        try {
            System.out.print("Enter description: ");
            String desc = scanner.nextLine();

            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter category: ");
            String category = scanner.nextLine();

            Expense expense = new Expense(desc, amount, category);
            service.addExpense(expense);

            System.out.println("Expense added.");

        } catch (InputMismatchException e) {
            System.out.println("Invalid amount!");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllExpenses() {
        List<Expense> expenses = service.getAllExpenses();
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        System.out.println("\nAll Expenses:");
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    private static void viewByCategory() {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        List<Expense> filtered = service.getExpensesByCategory(category);

        if (filtered.isEmpty()) {
            System.out.println("No expenses found for this category.");
            return;
        }

        System.out.println("\nExpenses in category '" + category + "':");
        for (Expense e : filtered) {
            System.out.println(e);
        }
    }

    private static void calculateTotal() {
        double total = service.calculateTotalExpenses();
        System.out.println("Total Expenses: " + total);
    }
}