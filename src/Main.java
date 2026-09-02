import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Banking System
        // Account Features:
        //      View amount/previous Transactions
        //      Deposit/Withdraw amounts
        //
        //      ArrayLists for multiple accounts (switch account option on main screen)
        //          To choose between accounts use for (i) inside a switch case
        //      More OOP stuff for different account types and account hierarchy



        System.out.println("Welcome to my Bank");
        System.out.print("Type in the name of the account holder to create an account: ");

        try (Scanner scanner = new Scanner(System.in)) {
            String accountHolder = scanner.nextLine();

            while (accountHolder.isBlank()) {
                System.out.print("That is an invalid name. Try again: ");
                accountHolder = scanner.nextLine();
            }

            Account account1 = new Account(accountHolder);
            System.out.println("You have created an account!");
            viewAccountUser(account1);

            // Labelled Break
            exitProgram:
            while(true) {
                System.out.println("""
                        Type in a number to select an option:
                            1. View Balance
                            2. Deposit Amount
                            3. Withdraw Amount
                            4. View Account Holder
                            5. Change Account Holder
                            6. Exit Program
                        """);

                try {
                    // Gets the input and leaves the newline string in the buffer
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        // View Balance
                        case 1 -> { viewBalance(account1); }
                        // Deposit amount
                        case 2 -> { depositAmount(scanner, account1); }
                        // Withdraw amount
                        case 3 -> { withdrawAmount(scanner, account1); }
                        // View Account Holder
                        case 4 -> { viewAccountUser(account1); }
                        // Change Account Holder
                        case 5 -> { changeAccountHolder(scanner, account1); }
                        // Exit Program
                        case 6 -> { break exitProgram; }
                        // Invalid option
                        default -> { System.out.printf("%d is an invalid choice\n", option); }
                    }
                } catch (InputMismatchException | NumberFormatException e){
                    System.out.println("Invalid Input");
                }
            }


        } finally {
            System.out.println("Thanks for banking with us. Goodbye!");
        }
    }
    private static void viewBalance(Account account){
        System.out.printf("Your current balance is: £%.2f\n\n", account.getBalance());
    }
    private static void viewAccountUser(Account account){
        System.out.printf("The account holder is: %s\n\n", account.getAccountHolder());
    }
    private static void changeAccountHolder(Scanner scanner, Account account) {
        System.out.print("Enter the name of the new Account Holder: ");
        String newAccountHolder = scanner.nextLine();
        account.setAccountHolder(newAccountHolder);
        viewAccountUser(account);
    }
    private static void depositAmount(Scanner scanner, Account account){
        System.out.println("Choose an amount to deposit: ");
        try {
            double amount = scanner.nextDouble();
            scanner.nextLine();

            if (amount < 0) {
                System.out.println("Cannot deposit a negative amount.");
                // return to Home Screen
                return;
            } else if (amount == 0) {
                System.out.println("There's nothing to deposit!");
                // return to Home Screen
                return;
            }
            account.deposit(amount);
            System.out.printf("£%.2f has been deposited into your account\n", amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
            scanner.nextLine();
        }
    }
    private static void withdrawAmount(Scanner scanner, Account account) {
        System.out.println("Choose an amount to withdraw: ");
        try {
            double amount = scanner.nextDouble();
            scanner.nextLine();
            if (amount < 0) {
                System.out.println("Cannot withdraw a negative amount.");
                // return to Home Screen
                return;
            } else if (amount == 0) {
                System.out.println("You can't withdraw an empty amount!");
                // return to Home Screen
                return;
            }
            try {
                account.withdraw(amount);
                System.out.printf("£%.2f has been withdrawn from your account\n", amount);
            } catch (InsufficientFundsException e) {
                System.out.println(e.getMessage());
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
            scanner.nextLine();
        }
    }


}
