import java.util.*;

class AccountManager {
    static void createAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("Enter your full name:");
        BankApp.customerName = sc.nextLine();
        System.out.println("Create a username:");
        String username = sc.nextLine();
        System.out.println("Create a password:");
        String password = sc.nextLine();  // Store the password here
        System.out.println("Enter your account number:");
        BankApp.accountNumber = sc.nextLine();
        System.out.println("REGISTRATION SUCCESSFUL!");
        System.out.println("---------------------------");
        BankApp.mainMenu();

        while (true) {
            displayOptions(BankApp.customerName);
            int option = sc.nextInt();
            if (option == 1) {
                authenticateUser(username, password);  // Pass username and password here
                break;
            } else if (option == 2) {
                System.exit(0);
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }


    static void displayOptions(String name) {
        System.out.println("Hello " + name + ", please choose an option:");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
    }

    static void authenticateUser(String username, String password) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username:");
        String inputUser = sc.nextLine();
        System.out.println("Enter password:");
        String inputPass = sc.nextLine();
        if (inputUser.equals(username) && inputPass.equals(password)) {
            BankApp.mainMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
            authenticateUser(username, password);
        }
    }
}

class BankTransaction {
    static void withdrawCash() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.println("Enter amount to withdraw:");
        int withdrawAmount = sc.nextInt();
        if (withdrawAmount <= BankApp.currentBalance) {
            BankApp.currentBalance -= withdrawAmount;
            BankApp.transactionHistory.add("Withdrawn: Rs " + withdrawAmount);
            System.out.println("Rs " + withdrawAmount + " withdrawn successfully.");
            System.out.println("---------------------------");
        } else {
            System.out.println("Insufficient balance.");
            System.out.println("---------------------------");
        }
        BankApp.mainMenu();
    }

    static void depositCash() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.print("Enter amount to deposit:");
        int depositAmount = sc.nextInt();
        BankApp.updateBalance(depositAmount);
        BankApp.transactionHistory.add("Deposited: Rs " + depositAmount);
        System.out.println("Rs " + depositAmount + " deposited successfully!");
        System.out.println("---------------------------");
        BankApp.mainMenu();
    }

    static void transferMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the recipient's name:");
        String recipient = sc.nextLine();
        System.out.println("Enter the recipient's account number:");
        String recipientAccNum = sc.nextLine();
        System.out.println("Enter the amount to transfer:");
        int transferAmount = sc.nextInt();
        if (transferAmount <= BankApp.currentBalance) {
            BankApp.currentBalance -= transferAmount;
            BankApp.transactionHistory.add("Transferred: Rs " + transferAmount + " to " + recipient);
            System.out.println("Rs " + transferAmount + " transferred successfully.");
            System.out.println("---------------------------");
        } else {
            System.out.println("Insufficient balance.");
            System.out.println("---------------------------");
        }
        BankApp.mainMenu();
    }
}

class BalanceChecker {
    static void checkAccountBalance() {
        System.out.println("------------------");
        System.out.println("Available balance:");
        BankApp.showBalance();
        System.out.println("---------------------------");
        BankApp.mainMenu();
    }
}

class TransactionHistory {
    static void viewTransactionHistory() {
        System.out.println("---------------------");
        System.out.println("Transaction History:");
        if (!BankApp.transactionHistory.isEmpty()) {
            for (String record : BankApp.transactionHistory) {
                System.out.println(record);
                System.out.println("---------------------");
            }
        } else {
            System.out.println("No transactions available.");
        }
        BankApp.mainMenu();
    }
}

public class BankApp {
    public static String customerName;
    public static int currentBalance = 0;
    public static String accountNumber;
    public static ArrayList<String> transactionHistory = new ArrayList<>();

    static void updateBalance(int amount) {
        currentBalance += amount;
    }

    static void showBalance() {
        System.out.println(currentBalance);
    }

    public static void homepage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME TO THE BANKING SYSTEM");
        System.out.println("--------------------------");
        System.out.println("Select an option:");
        System.out.println("1. Create Account");
        System.out.println("2. Exit");
        System.out.println("Enter your choice:");
        int choice = sc.nextInt();
        if (choice == 1) {
            AccountManager.createAccount();
        } else if (choice == 2) {
            System.exit(0);
        } else {
            System.out.println("Invalid choice! Please try again.");
            homepage();
        }
    }

    static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME, " + BankApp.customerName + "!");
        System.out.println("---------------------");
        System.out.println("Select an option:");
        System.out.println("1. Withdraw Cash");
        System.out.println("2. Deposit Cash");
        System.out.println("3. Transfer Money");
        System.out.println("4. Check Balance");
        System.out.println("5. View Transaction History");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                BankTransaction.withdrawCash();
                break;
            case 2:
                BankTransaction.depositCash();
                break;
            case 3:
                BankTransaction.transferMoney();
                break;
            case 4:
                BalanceChecker.checkAccountBalance();
                break;
            case 5:
                TransactionHistory.viewTransactionHistory();
                break;
            case 6:
                System.exit(0);
        }
    }

    public static void main(String[] args) {
        homepage();
    }
}
