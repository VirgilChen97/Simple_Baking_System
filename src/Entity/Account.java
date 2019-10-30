package Entity;

import java.io.Serializable;

/**
 * Base account entity
 * @author Yifeng Chen
 * @version 1.0
 */
public abstract class Account implements Serializable {

    protected int pin;
    protected int accountNumber;
    protected String name;
    protected String address;
    protected String birthday;
    protected String password;
    protected Double Balance;
    protected Double uncleared_balance;
    protected int age;
    protected Boolean isSuspended;
    protected String type;

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }

    public Double getUncleared_balance() {
        return uncleared_balance;
    }

    public void setUncleared_balance(Double uncleared_balance) {
        this.uncleared_balance = uncleared_balance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getSuspended() {
        return isSuspended;
    }

    public void setSuspended(Boolean suspended) {
        isSuspended = suspended;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Account(){};

    /**
     * Constructor of base account
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
    public Account(String type, int pin, int accountNumber, String name, String address, String birthday, String password, Double balance, int age) {
        this.type = type;
        this.pin = pin;
        this.accountNumber = accountNumber;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.password = password;
        this.Balance = balance;
        this.uncleared_balance = 0.0;
        this.age = age;
        this.isSuspended = false;
    }

    /**
     * Deposit with cash
     * @param number    Number of funds that you want to deposit
     */
    public void deposit_cash(Double number){
        Balance = Balance + number;
    }

    /**
     * Deposit with cheque
     * @param number    Number of funds that you want to deposit
     */
    public void deposit_cheque(Double number){
        uncleared_balance = uncleared_balance + number;
    }

    /**
     * A parent with draw method, this has no function,
     * @param number    Number of funds that you want to withdraw
     * @return  isSuccess
     */
    public abstract int withdraw(Double number);


}
