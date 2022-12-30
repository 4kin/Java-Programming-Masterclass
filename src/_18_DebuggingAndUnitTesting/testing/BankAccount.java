package _18_DebuggingAndUnitTesting.testing;

public class BankAccount {

    private String firstName;
    private String lastName;
    private Double balance;
    public static final int CHECKING = 1;
    public static final int SAVING = 2;

    private int accountType = SAVING;

    public BankAccount(String firstName, String lastName, Double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public BankAccount(String firstName, String lastName, Double balance, int typeOfAccount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.accountType = typeOfAccount;
    }


    public double deposit(double amount, boolean branch){
        balance += amount;
        return balance;
    }

    public double withdraw(double amount, boolean branch){
        if(amount > 500.00 && !branch) {
            throw new IllegalArgumentException();
        }
        balance -= amount;
        return balance;
    }

    public double getBalane() {
        return balance;
    }

    public boolean isChecking() {
        return accountType == CHECKING;
    }
}
