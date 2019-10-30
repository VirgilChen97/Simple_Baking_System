package Entity;

import tools.DateTools;

/**
 * Entity for a Junior account
 * @author Yifeng Chen
 * @version 1.0
 */

public class SaverAccount extends Account {
    protected Boolean isReserved;
    protected String reserveDate;

    public SaverAccount(){};

    /**
     *
     * @param type      Account type
     * @param pin       Account PIN
     * @param accountNumber Accound ID
     * @param name      User name
     * @param address   User address
     * @param birthday  User birthday
     * @param password  User password
     * @param balance   Initial balance
     * @param age       USer age
     * @param isReserved    Whether this account has a reservation
     * @param reserveDate   Reservation date
     */
    public SaverAccount(String type, int pin, int accountNumber, String name, String address, String birthday, String password, Double balance, int age, Boolean isReserved, String reserveDate) {
        super(type, pin, accountNumber, name, address, birthday, password, balance, age);
        this.isReserved = isReserved;
        this.reserveDate = reserveDate;
    }

    /**
     * Saver version of withdraw, with reservation check
     * @param number how much want to withdraw
     * @return isSuccess
     */
    @Override
    public int withdraw(Double number){
        if (!isReserved){
            return 3;
        }
        long time = DateTools.getDistanceDays(reserveDate,DateTools.getDate());
        if (time!=0){
            return 2;
        }
        if( number > Balance){
            return 1;
        }
        Balance = Balance - number;
        isReserved = false;
        reserveDate = null;
        return 0;
    }

    /**
     * Reserve service for this account
     * @param date Reservation date
     * @return isSuccess
     */
    public Boolean reserve(String date) {
        isReserved = true;
        reserveDate = date;
        return true;
    }

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }

    public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }
}
