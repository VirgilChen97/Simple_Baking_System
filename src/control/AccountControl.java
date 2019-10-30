package control;

import Entity.Account;
import Entity.CurrentAccount;
import Entity.JuniorAccount;
import Entity.SaverAccount;
import tools.DateTools;

import javax.swing.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

/**
 * Class to control account related operation
 * @author Yifeng Chen
 * @version 1.3
 */
public class AccountControl {
    private static ArrayList<Account> accounts;
    private static Boolean isCreated = false;
    public AccountControl() {
        if(!isCreated) {
            accounts = new ArrayList<>();
            isCreated = true;
        }
    }

    /**
     * Check if the login information is correct
     * @param id       User ID
     * @param pin      User PIN
     * @param password User password
     * @return account entity contains all information
     */
    public Account login(String id, String pin, String password) {
        for (int j = 0; j < accounts.size(); j++) {
            Account temp = accounts.get(j);
            if (Integer.parseInt(id)==temp.getAccountNumber() && Integer.parseInt(pin)==temp.getPin() && password.equals(temp.getPassword())) {
                return temp;
            }
        }
        return null;
    }

    /**
     * Create an account entity from given information
     *
     * @param type
     * @param name
     * @param address
     * @param Birthday_year
     * @param birthday_month
     * @param birthday_day
     * @param password
     * @return success or failed
     */
    public Account signup(
            String type,
            String name,
            String address,
            String Birthday_year,
            String birthday_month,
            String birthday_day,
            String password
    ) {
        String birthday = Birthday_year + "-" + birthday_month + "-" + birthday_day;
        long days = DateTools.getDistanceDays(birthday, DateTools.getDate());
        int age = Math.round(days / 365);
        Account account = null;
        switch (type) {
            case "Saver": {
                account = new SaverAccount(type, generate_pin(), accounts.size() + 1, name, address, birthday, password, 5.0, age, false, null);
                accounts.add(account);
            }
            break;
            case "Junior": {
                if (age > 16) return null;
                account = new JuniorAccount(type, generate_pin(), accounts.size() + 1, name, address, birthday, password, 5.0, age);
                accounts.add(account);
            }
            break;
            case "Current": {
                account = new CurrentAccount(type, generate_pin(), accounts.size() + 1, name, address, birthday, password, 5.0, age, Math.random() * 100 + 100);
                accounts.add(account);
            }
        }
        return account;
    }

    /**
     * Delete given account
     * @param account the account you want to delete
     */
    public void delete(Account account){
        for (int j = 0; j < accounts.size(); j++) {
            Account temp = accounts.get(j);
            if (account.getAccountNumber()==temp.getAccountNumber() && account.getPin()==temp.getPin()) {
                accounts.remove(account);
            }
        }
    }

    /**
     * Generate a unique pin
     * @return unique pin
     */
    private int generate_pin() {
        Boolean flag = true;
        String pinstr = "";
        int pin;
        while (true) {
            for (int i = 0; i < 8; i++) {
                String digit = String.valueOf((int) (Math.random() * 10));
                pinstr = pinstr + digit;
            }
            pin = Integer.parseInt(pinstr);
            for (int j = 0; j < accounts.size(); j++) {
                Account temp = accounts.get(j);
                if (pin == temp.getPin()) {
                    flag = false;
                }
            }
            if (flag) break;
        }
        return pin;
    }

    /**
     * Change user information for reservation
     * @param ID
     * @param pin
     * @param Date
     * @return task status, 1:already reserved; 0: success; -1:not saver; -2:no such account
     */
    public int reserveControl(String ID, String pin, String Date) {
        Boolean success=false;
        for (int j = 0; j < accounts.size(); j++) {
            Account temp = accounts.get(j);
            if (Integer.parseInt(ID)==temp.getAccountNumber() && Integer.parseInt(pin)==temp.getPin()) {
                SaverAccount reserver = (SaverAccount) temp;
                if(!reserver.getType().equals("Saver")){
                    return -1;
                }
                success = reserver.reserve(Date);
                if(success) return 0;
                else return 1;
            }
        }
        return -2;
    }

    public void saveData(){
        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("accounts.xml")));
            encoder.writeObject(accounts);
            encoder.close();
        }catch (FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Data can't be saved","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadData(){
        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("accounts.xml")));
            accounts= (ArrayList<Account>) decoder.readObject();
            decoder.close();
            isCreated = true;
        }catch (FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Data can't be loaded","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }
}