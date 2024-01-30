import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  

class Account {
    private String name;
    private int accountNumber;
    private String creationDate;
    private double balance;
    private String type;

    // Constructors, getters, and setters

    public Account(String name, int accountNumber,  double balance, String type) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;

        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(date); 

        this.creationDate = strDate;
    }

    public String getName() {
        return name;
    }

    void setName(String n)
    {
        this.name = n;
    }

    public int getAccountNumber() {
        return accountNumber;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", accountNumber=" + accountNumber +
                ", creationDate='" + creationDate + '\'' +
                ", balance=" + balance +
                '}';
    }
}

class Bank {
    private List<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public void createAccount(String name, int accountNumber,  double initialBalance, String type) {
        Account newAccount = new Account(name, accountNumber,  initialBalance, type);
        accounts.add(newAccount);
        System.out.println("******  Account is successfully created.  ******");
    }

    public void displayAllAccounts() {
        boolean f=true;
        for (Account account : accounts) {
            f = false;

            System.out.println(account);
        }
        if(f) System.out.println("******  No account found!  ******");

    }

    public void updateAccount(int accountNumber, String newName) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                account.setName(newName);
                System.out.println("******  Account is successfully updated.  ******");
                return;
            }
        }
        System.out.println("******  Account is not found for updating.  ******");
    }

    public void deleteAccount(int accountNumber) {
        accounts.removeIf(account -> account.getAccountNumber() == accountNumber);
        System.out.println("******  Account deleted successfully!  ******");
    }

    public void depositAmount(int accountNumber, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                account.setBalance(account.getBalance() + amount);
                System.out.println("******  Amount deposited successfully!  ******");
                return;
            }
        }
        System.out.println("******  Account not found for depositing.  ******");
    }

    public void withdrawAmount(int accountNumber, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                if (account.getBalance() >= amount) {
                    account.setBalance(account.getBalance() - amount);
                    System.out.println("******  Amount withdrawn successfully!  ******");
                } else {
                    System.out.println("******  Insufficient funds for withdrawal.  ******");
                }
                return;
            }
        }
        System.out.println("******  Account not found for withdrawal.  ******");
    }

    public Account searchAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}

public class BankingApplication {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Create a new account
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter account number: ");
                    int accountNumber = scanner.nextInt();
                    double initialBalance=10;
                    boolean f=false;
                    int i=3;
                    while(i>0)
                    {
                        i--;
                        if(i==0)
                            System.out.print("[ You have one last chance to enter an initial balance. ]\n");
                        System.out.print("Enter initial balance: ");
                        initialBalance = scanner.nextDouble();
                        if(initialBalance<10)
                        {
                            if(i==0) f=true;
                            else
                                System.out.print("****** You need at least £10 to open an account. ******\n");
                        }
                            
                        else
                            break;
                    }
                    if(f) break;
                    
                    i=0;
                    String type="Current account";
                    while(true)
                    {
                        System.out.print("Select Account type: \n1)Current account \n2)Saving account \n3)Salary Account\nEnter type:");
                        double typeN = scanner.nextDouble();
                        
                        if(typeN==1) type = "Current account";
                        else if(typeN==2) type = "Saving account";
                        else if(typeN==3) type = "Salary account";
                        else
                        {
                            i++;
                            if(i==3) System.out.print("[ You have one last chance to enter valid type number. ]\n");
                            else if(i>3) {f=true; break;}
                            else System.out.print("****** Please enter a valid type number. ******\n");
                            continue;
                        }

                        break;
                    }
                    if(f) break;

                    bank.createAccount(name, accountNumber, initialBalance,type);
                    break;
                case 2:
                    // Display all accounts
                    bank.displayAllAccounts();
                    break;
                case 3:
                    // Update an account
                    System.out.print("Enter account number to update: ");
                    int updateAccountNumber = scanner.nextInt();
                    System.out.print("Enter new name: ");
                    String newName = scanner.next();
                    bank.updateAccount(updateAccountNumber, newName);
                    break;
                case 4:
                    // Delete an account
                    System.out.print("Enter account number to delete: ");
                    int deleteAccountNumber = scanner.nextInt();
                    bank.deleteAccount(deleteAccountNumber);
                    break;
                case 5:
                    // Deposit an amount into an account
                    System.out.print("Enter account number for deposit: ");
                    int depositAccountNumber = scanner.nextInt();
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    bank.depositAmount(depositAccountNumber, depositAmount);
                    break;
                case 6:
                    // Withdraw an amount from an account
                    System.out.print("Enter account number for withdrawal: ");
                    int withdrawalAccountNumber = scanner.nextInt();
                    
                    // 
                    double withdrawalAmount;
                    i=0;
                    boolean flag = false;
                    while(true)
                    {
                        System.out.print("Enter withdrawal amount: ");
                        withdrawalAmount = scanner.nextDouble();
                        if(withdrawalAmount>=10)
                            break;
                        else 
                        {
                            i++;
                            System.out.print("You need to withdraw minimum £10.\n");
                            if(i>3) {flag=true; break;}
                            if(i==3)
                            {
                                System.out.print("You have one more chance.\n");
                            }
                        }
                    }
                    if(!flag)
                        bank.withdrawAmount(withdrawalAccountNumber, withdrawalAmount);
                    break;
                case 7:
                    // Search for an account
                    System.out.print("Enter account number to search: ");
                    int searchAccountNumber = scanner.nextInt();
                    Account searchedAccount = bank.searchAccount(searchAccountNumber);
                    if (searchedAccount != null) {
                        System.out.println("******  Account found: " + searchedAccount + "  ******");
                    } else {
                        System.out.println("******  Account not found.  ******");
                    }
                    break;
                case 8:
                    System.out.println("####-----  Exiting  -----####");
                    System.exit(0);
                    break;
                default:
                    System.out.println("*****  Invalid! Please enter a number between 1 and 8.  *****");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Banking Application Menu:");
        System.out.println("1. Create a new account");
        System.out.println("2. Display all accounts");
        System.out.println("3. Update an account");
        System.out.println("4. Delete an account");
        System.out.println("5. Deposit an amount into your account");
        System.out.println("6. Withdraw an amount from your account");
        System.out.println("7. Search for an account");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }
}
