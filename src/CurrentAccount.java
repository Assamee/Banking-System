import java.math.BigDecimal;

public class CurrentAccount extends Account {

    // Attributes
    BigDecimal overdraftLimit;

    CurrentAccount(String accountHolder){
        super(accountHolder);
        overdraftLimit = new BigDecimal("0.00");
    }

    // Getters
    public BigDecimal getOverdraftLimit(){
        return this.overdraftLimit;
    }

    // Setters
    public void setOverdraftLimit(BigDecimal OverdraftLimit) {
        if (OverdraftLimit.signum() == -1){
            throw new IllegalArgumentException("Can't have a negative Overdraft");
        } else {
            this.overdraftLimit = OverdraftLimit;
        }
    }

    @Override
    protected BigDecimal getMinimumBalance() {
        return overdraftLimit.negate();
    }
}
