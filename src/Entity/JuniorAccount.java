package Entity;

/**
 * Entity for a Junior account
 * @author Yifeng Chen
 * @version 1.0
 */

public class JuniorAccount extends Account{
    public JuniorAccount(){}

    /**
     * Constructor for junior account
     * @param type      Account type
     * @param pin       Account PIN
     * @param accountNumber Accound ID
     * @param name      User name
     * @param address   User address
     * @param birthday  User birthday
     * @param password  User password
     * @param balance   Initial balance
     * @param age       USer age
     */
    public JuniorAccount(String type, int pin, int accountNumber, String name, String address, String birthday, String password, Double balance, int age) {
        super(type, pin, accountNumber, name, address, birthday, password, balance, age);
    }

    /**
     * Junior version withdraw,no overdrafting,no reservation
     * @param number how much to withdraw
     * @return isSuccess
     */
    @Override
    public int withdraw(Double number){
        if(number > Balance){
            return 1;
        }
        Balance = Balance - number;
        return 0;
    }
}
