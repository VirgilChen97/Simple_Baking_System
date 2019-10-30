package Entity;

import java.util.function.DoubleBinaryOperator;

/**
 * Entity for a current account
 * @author Yifeng Chen
 * @version 1.0
 */
public class CurrentAccount extends Account{

    private Double overdraft_limit;

    public CurrentAccount(){};

    /**
     * constructor for current account
     * @param type      Account type
     * @param pin       Account PIN
     * @param accountNumber Accound ID
     * @param name      User name
     * @param address   User address
     * @param birthday  User birthday
     * @param password  User password
     * @param balance   Initial balance
     * @param age       User age
     * @param overdraft_limit amount of money this account can overdraft
     */
    public CurrentAccount(String type,int pin, int accountNumber, String name, String address, String birthday, String password, Double balance, int age, Double overdraft_limit) {
        super(type ,pin, accountNumber, name, address, birthday, password, balance, age);
        this.overdraft_limit = overdraft_limit;
    }

    /**
     * Current Account version of withdraw with overdraft
     * @param number how much want to withdraw
     * @return isSuccess
     */
    @Override
    public int withdraw(Double number){
        if(overdraft_limit+Balance < number){
            return 1;
        }
        Balance = Balance - number;
        return 0;
    }

    public Double getOverdraft_limit() {
        return overdraft_limit;
    }

    public void setOverdraft_limit(Double overdraft_limit) {
        this.overdraft_limit = overdraft_limit;
    }
}
