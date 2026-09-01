import java.math.BigDecimal;

public class Account {

    // Attributes
    private String accountHolder;
    private BigDecimal balance;
    private static int nextAccountID = 1;
    private final int accountID;


    // Constructor
    Account(String accountHolder){
        this.accountHolder = accountHolder;
        this.balance = BigDecimal.ZERO;
        this.accountID = nextAccountID;
        nextAccountID++;
    }

    // Getters
    public String getAccountHolder(){
        return this.accountHolder;
    }
    public int getAccountID(){
        return this.accountID;
    }
    public BigDecimal getBalance(){
        return this.balance;
    }

    // Setters
    public void setAccountHolder(String newName){
        this.accountHolder = newName;
    }

    // Withdraw / Deposit
    public void withdraw(double amount){
        if (amount <= 0) {
            throw new IllegalArgumentException("Can only withdraw positive amounts");
        }
        BigDecimal accurateAmount = BigDecimal.valueOf(amount);
        BigDecimal newBalance = this.balance.subtract(accurateAmount);

        // If the withdrawal doesn't result in a negative balance, go ahead with the transaction
        // .compareTo() returns -1(<), 0(=), or 1(>)
        if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
            this.balance = newBalance;
        } else {
            throw new InsufficientFundsException(
                    String.format("Cannot withdraw £%.2f - your balance is £%.2f", amount, this.getBalance()));
        }
    }

    public void deposit(double amount){
        if (amount <= 0) {
            throw new IllegalArgumentException("Can only deposit positive amounts");
        }
        BigDecimal accurateAmount = BigDecimal.valueOf(amount);
        this.balance = this.balance.add(accurateAmount);
    }

}
