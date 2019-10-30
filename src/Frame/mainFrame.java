/*
 * Created by JFormDesigner on Mon May 21 22:00:27 CST 2018
 */

package Frame;

import Entity.Account;
import Entity.CurrentAccount;
import Entity.JuniorAccount;
import Entity.SaverAccount;
import control.AccountControl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;

/**
 * User interface after the user login the system
 * @author Yifeng Chen
 * @version 1.2
 */
public class mainFrame extends JFrame implements ActionListener {
    public mainFrame(Account account) {
        this.account = account;
        initComponents();
        refreshComponents();
        Withdraw.addActionListener(this);
        cash.addActionListener(this);
        cheque.addActionListener(this);
        suspend.addActionListener(this);
        delete.addActionListener(this);
        exit.addActionListener(this);
        setVisible(true);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                new AccountControl().saveData();
                new WelcomeFrame().setVisible(true);
            }
        });
    }

    /**
     * refresh components in the frame
     */
    private void refreshComponents(){
        BalanceDisplay.setText(String.valueOf(account.getBalance()));
        IDDisplay.setText(String.valueOf(account.getAccountNumber()));
        PinDisplay.setText(String.valueOf(account.getPin()));
        TypeDisply.setText(String.valueOf(account.getType()));
        UnclearDisplay.setText(String.valueOf(account.getUncleared_balance()));
        if("Current".equals(account.getType())){
            CurrentAccount currentaccount = (CurrentAccount)account;
            LimitDisplay.setText(String.valueOf(currentaccount.getOverdraft_limit()));
        }else {
            LimitDisplay.setText("0");
        }
    }

    /**
     * init component in the frame
     */
    private void initComponents() {
        Display = new JPanel();
        label3 = new JLabel();
        BalanceDisplay = new JLabel();
        label5 = new JLabel();
        IDDisplay = new JLabel();
        label7 = new JLabel();
        PinDisplay = new JLabel();
        label9 = new JLabel();
        TypeDisply = new JLabel();
        label11 = new JLabel();
        UnclearDisplay = new JLabel();
        label13 = new JLabel();
        LimitDisplay = new JLabel();
        panel1 = new JPanel();
        Withdraw = new JButton();
        cash = new JButton();
        cheque = new JButton();
        suspend = new JButton();
        delete = new JButton();
        exit = new JButton();

        //======== this ========
        setTitle("Bank System");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== Display ========
        {
            Display.setLayout(new GridLayout(0, 2, 10, 10));

            //---- label3 ----
            label3.setText("Balance");
            label3.setFont(new Font("Arial", Font.PLAIN, 14));
            label3.setHorizontalAlignment(SwingConstants.RIGHT);
            Display.add(label3);

            //---- BalanceDisplay ----
            BalanceDisplay.setFont(new Font("Arial", Font.PLAIN, 14));
            Display.add(BalanceDisplay);

            //---- label5 ----
            label5.setText("Account ID");
            label5.setFont(new Font("Arial", Font.PLAIN, 14));
            label5.setHorizontalAlignment(SwingConstants.RIGHT);
            Display.add(label5);

            //---- IDDisplay ----
            IDDisplay.setFont(new Font("Arial", Font.PLAIN, 14));
            Display.add(IDDisplay);

            //---- label7 ----
            label7.setText("Your PIN");
            label7.setFont(new Font("Arial", Font.PLAIN, 14));
            label7.setHorizontalAlignment(SwingConstants.RIGHT);
            Display.add(label7);

            //---- PinDisplay ----
            PinDisplay.setFont(new Font("Arial", Font.PLAIN, 14));
            Display.add(PinDisplay);

            //---- label9 ----
            label9.setText("Account Type");
            label9.setFont(new Font("Arial", Font.PLAIN, 14));
            label9.setHorizontalAlignment(SwingConstants.RIGHT);
            Display.add(label9);

            //---- TypeDisply ----
            TypeDisply.setFont(new Font("Arial", Font.PLAIN, 14));
            Display.add(TypeDisply);

            //---- label11 ----
            label11.setText("Unclear funds");
            label11.setFont(new Font("Arial", Font.PLAIN, 14));
            label11.setHorizontalAlignment(SwingConstants.RIGHT);
            Display.add(label11);

            //---- UnclearDisplay ----
            UnclearDisplay.setFont(new Font("Arial", Font.PLAIN, 14));
            Display.add(UnclearDisplay);

            //---- label13 ----
            label13.setText("OverDraw limit");
            label13.setFont(new Font("Arial", Font.PLAIN, 14));
            label13.setHorizontalAlignment(SwingConstants.RIGHT);
            Display.add(label13);

            //---- LimitDisplay ----
            LimitDisplay.setFont(new Font("Arial", Font.PLAIN, 14));
            Display.add(LimitDisplay);
        }
        contentPane.add(Display);
        Display.setBounds(25, 30, 230, 275);

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayout(0, 1, 10, 10));

            //---- Withdraw ----
            Withdraw.setText("Withdraw");
            Withdraw.setFont(new Font("Arial", Font.PLAIN, 12));
            panel1.add(Withdraw);

            //---- cash ----
            cash.setText("Deposit:cash");
            cash.setFont(new Font("Arial", Font.PLAIN, 12));
            panel1.add(cash);

            //---- cheque ----
            cheque.setText("Deposit:cheque");
            cheque.setFont(new Font("Arial", Font.PLAIN, 12));
            panel1.add(cheque);

            //---- suspend ----
            suspend.setText("Suspend");
            suspend.setFont(new Font("Arial", Font.PLAIN, 12));
            panel1.add(suspend);

            //---- delete ----
            delete.setText("Delete Account");
            delete.setFont(new Font("Arial", Font.PLAIN, 12));
            panel1.add(delete);

            //---- exit ----
            exit.setText("Exit");
            exit.setFont(new Font("Arial", Font.PLAIN, 12));
            panel1.add(exit);
        }
        contentPane.add(panel1);
        panel1.setBounds(280, 30, 145, 275);

        contentPane.setPreferredSize(new Dimension(455, 335));
        pack();
        setLocationRelativeTo(getOwner());
    }

    /**
     * Listener for withdraw, deposit with cash, deposit with cheque, suspend and delete
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        if(button == Withdraw){
            String withdraw = JOptionPane.showInputDialog("Please input the value that you would like to withdraw");
            int success = account.withdraw(Double.valueOf(withdraw));
            if(success == 0){JOptionPane.showMessageDialog(this,"Withdraw Successful","Success",JOptionPane.INFORMATION_MESSAGE);}
            else if(success == 1){JOptionPane.showMessageDialog(this,"Insufficient funds","Failed",JOptionPane.ERROR_MESSAGE);}
            else if(success == 2){JOptionPane.showMessageDialog(this,"Your reservation is not today","Failed",JOptionPane.ERROR_MESSAGE);}
            else if(success == 3){JOptionPane.showMessageDialog(this,"Your need to reserve first","Failed",JOptionPane.ERROR_MESSAGE);}
            refreshComponents();
        }else if(button == cash){
            String deposit = JOptionPane.showInputDialog("Please input the value that you would like to deposit with cash");
            account.deposit_cash(Double.parseDouble(deposit));
            refreshComponents();
        }else if(button == cheque){
            String deposit = JOptionPane.showInputDialog("Please input the value that you would like to deposit with cheque");
            account.deposit_cheque(Double.parseDouble(deposit));
            refreshComponents();
        }else if(button == suspend){
            int choose = JOptionPane.showConfirmDialog(this,"Are you sure you want to suspend your account?","Suspend",JOptionPane.YES_NO_OPTION);
            if(choose == 0) {
                account.setSuspended(true);
                JOptionPane.showMessageDialog(this, "Your account is successfully suspended", "Success", JOptionPane.INFORMATION_MESSAGE);
                ArrayList<Account> a = AccountControl.getAccounts();
                System.out.println("a");
            }
        }else if(button == delete){
            int choose = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete your account?","Delete",JOptionPane.YES_NO_OPTION);
            if(choose == 0){
                String balance = String.valueOf(account.getBalance());
                new AccountControl().delete(account);
                String message = "Your account is successfully suspended\n"+"Please take the money in your account: "+balance;
                JOptionPane.showMessageDialog(this,message,"Success",JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
                new WelcomeFrame().setVisible(true);
            }
        }else if(button == exit){
            this.setVisible(false);
            new AccountControl().saveData();
            new WelcomeFrame().setVisible(true);
        }
    }

    private Account account;
    private JPanel Display;
    private JLabel label3;
    private JLabel BalanceDisplay;
    private JLabel label5;
    private JLabel IDDisplay;
    private JLabel label7;
    private JLabel PinDisplay;
    private JLabel label9;
    private JLabel TypeDisply;
    private JLabel label11;
    private JLabel UnclearDisplay;
    private JLabel label13;
    private JLabel LimitDisplay;
    private JPanel panel1;
    private JButton Withdraw;
    private JButton cash;
    private JButton cheque;
    private JButton suspend;
    private JButton delete;
    private JButton exit;
}
